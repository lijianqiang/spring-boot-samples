
var panelHandler = new mdui.Panel('#placer-panel');

var URLs = {
    create: "/api/placers"
}

var placerPanel = new Vue({
	el: "#placer-panel",
	data: {
		active: false,
		address: null,
		latitude: null,
		longitude: null,
		provinceName: "",
		cityName: "",
		regionName: "",
		ylength: 1000,
		xlength: 1000,
		name: null,
        intro: null,
        enabled: false,
        placeType: 1,
        fullAddress: ""
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
			axios.post(URLs.create, this.buildParams()
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
			params.enabled = false;
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
var point = new BMap.Point(119.33022111,26.0471255);
	
map.centerAndZoom(point, 14);
map.enableScrollWheelZoom(); //开启鼠标滚轮缩放

map.enableContinuousZoom();

function myFun(result){
	var cityName = result.name;
	console.log(result);
	map.setCenter(cityName);
		//alert("当前定位城市:"+cityName);
}

var myCity = new BMap.LocalCity();
myCity.get(myFun);
	
var geoc = new BMap.Geocoder();
function showInfo(e){
	placerPanel.longitude = e.point.lng;
	placerPanel.latitude = e.point.lat;
	var pt = e.point;
	var marker = new BMap.Marker(pt);
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

