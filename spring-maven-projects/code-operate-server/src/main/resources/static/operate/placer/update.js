
var panelHandler = new mdui.Panel('#placer-panel');

var URLs = {
    update: "/api/placers/"
}

var PlacerInfo = {
	longitude: document.getElementById("placer-longitude").value,
	latitude: document.getElementById("placer-latitude").value,
	name: document.getElementById("placer-name").value,	
	id: document.getElementById("placer-id").value,
	address: document.getElementById("placer-address").value,
	ylength: document.getElementById("placer-ylength").value,
	xlength: document.getElementById("placer-xlength").value,
	intro: document.getElementById("placer-intro").value,
	enable: document.getElementById("placer-enable").value,
}

var placerPanel = new Vue({
	el: "#placer-panel",
	data: {
		active: false,
		address: PlacerInfo.address,
		latitude: PlacerInfo.latitude,
		longitude: PlacerInfo.longitude,
		provinceName: "",
		cityName: "",
		regionName: "",
		ylength: PlacerInfo.ylength,
		xlength: PlacerInfo.xlength,
		name: PlacerInfo.name,
        intro: PlacerInfo.intro,
        enable: PlacerInfo.enable,
        placeType: PlacerInfo.placeType,
        fullAddress: PlacerInfo.address,
	},
	computed: {
	    // a computed getter
	    fullDistrict: function () {
	      // `this` points to the vm instance
	      return this.cityName + this.regionName + this.address;
	    }
	},
	methods: {
		submitPlacerForm: function() {
			this.active = true;
			var that = this;
			axios.put(URLs.update + PlacerInfo.id, this.buildParams()
			  )
			  .then(function (response) {
			    console.log(response);
			    if (response.status === 200 && response.data.code === 0) {
			    	mdui.alert('创建成功', function(){
			    		console.log("创建成功,跳转...");
			    		onClickBack();
			    	});
			    } else {
			    	console.log(response.data.code);
			    	mdui.alert('失败原因:' + response.data.message, '创建失败');
			    }
			  })
			  .catch(function (error) {
			    console.log(error);
		    	mdui.alert('失败原因:' + error, '创建失败');
			  });
		},
		buildParams: function() {
			var params = {};
			params.address = this.fullDistrict;
			params.latitude = this.latitude;
			params.longitude = this.longitude;
			params.provinceName = this.provinceName;
			params.cityName = this.cityName;
			params.regionName = this.regionName;
			params.ylength = this.ylength;
			params.xlength = this.xlength;
			params.name = this.name;
			params.intro = this.intro;
			params.enable = true;
			params.placeType = 1;
			
			return params;
		},
		checkParams: function() {
			if (isEmpty(this.name)) {
				mdui.snackbar({
			  		  message: "请填写正确名称",
			  		  timeout: 2000
			  		});
				return false;
			}
			if (isEmpty(this.address)) {
				mdui.snackbar({
			  		  message: "街道地址定位有误",
			  		  timeout: 2000
			  		});
				return false;
			}
			return true;
		},
		closeBaseInfo: function() {
			panelHandler.close("#base-info-item");
		},
		closeLocationInfo: function() {
			panelHandler.close("#location-info-item");
		}
	}
});

function isEmpty(src) {
	if (src == null || src.length < 1) {
		return true;
	}
	return false;
}

function onClickSubmit() {
	var isOk = placerPanel.checkParams();
	console.log("onClickSubmit:" + isOk);
	if (isOk !== true) {
		return;
	}
	placerPanel.submitPlacerForm();
}

function onClickBack() {
	window.location.href = "/area/index.html";
}


//百度地图API功能
var map = new BMap.Map("allmap", {enableMapClick:false});//构造底图时，关闭底图可点功能
var point = new BMap.Point(PlacerInfo.longitude, PlacerInfo.latitude);
	
map.centerAndZoom(point, 18);
//map.enableScrollWheelZoom(); //开启鼠标滚轮缩放

map.enableContinuousZoom();

var marker = new BMap.Marker(point);
map.addOverlay(marker);
console.log("name:" + PlacerInfo.name);
var label = new BMap.Label(PlacerInfo.name, {offset:new BMap.Size(20,-20)});
marker.setLabel(label); //添加百度label
	
var geoc = new BMap.Geocoder();
function showInfo(e){
	map.clearOverlays();
	placerPanel.longitude = e.point.lng;
	placerPanel.latitude = e.point.lat;
	var pt = e.point;
	marker = new BMap.Marker(pt);
	map.addOverlay(marker);
	geoc.getLocation(pt, function(rs){
		var addComp = rs.addressComponents;
		placerPanel.fullAddress = rs.address;
		console.log(addComp);
		placerPanel.provinceName = addComp.province;
		placerPanel.cityName = addComp.city;
		placerPanel.regionName = addComp.district;
		placerPanel.address = "" + addComp.street + addComp.streetNumber;
	});        
}
map.addEventListener("click", showInfo);

