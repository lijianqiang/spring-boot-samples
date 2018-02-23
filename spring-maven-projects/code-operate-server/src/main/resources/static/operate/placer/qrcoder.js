
var panelHandler = new mdui.Panel('#qrcoder-panel');

var URLs = {
	create: "/api/qrcoders",
    qrcoderList: "/api/qrcoders",
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

var QrcoderParams = {
	placerId: PlacerInfo.id,
	limit: 200,
}

var qrcoderPanel = new Vue({
	el: "#qrcoder-panel",
	data: {
		qrcoders: [],
		name: null,
		latitude: null,
		longitude: null,
        enable: false,
        indoor: false,
        placerId: PlacerInfo.id
	},
	methods: {
		createQrocder: function() {
			var that = this;
			axios.post(URLs.create, this.buildParams()
			  )
			  .then(function (response) {
			    console.log(response);
			    if (response.status === 200 && response.data.code === 0) {
			    	mdui.alert('创建成功');
			    	var newqrcoder = response.data.data;
			    	that.qrcoders.push(newqrcoder)
			    	that.resetTempQrcoder();
			    	var qmarker = buildQrcoderMarker(newqrcoder.longitude, newqrcoder.latitude);
					map.addOverlay(qmarker);
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
			params.latitude = this.latitude;
			params.longitude = this.longitude;
			params.name = this.name;
			params.enable = this.enable;
			params.indoor = false;
			params.placerId = this.placerId;
			
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
			if (isEmpty(this.latitude)) {
				mdui.snackbar({
			  		  message: "地址定位有误",
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
		},
		submitTempQrcoder: function() {
			var isOk = this.checkParams();
			console.log("submitTempQrcoderClick:" + isOk);
			if (isOk !== true) {
				return;
			}
			this.createQrocder();
		},
		resetTempQrcoder: function() {
			this.name = null;
			this.latitude = null;
			this.longitude = null;
			clearTempMarker();
		}
	}
});

function isEmpty(src) {
	if (src == null || src.length < 1) {
		return true;
	}
	return false;
}


//百度地图API功能
var map = new BMap.Map("allmap", {enableMapClick:false});//构造底图时，关闭底图可点功能
var point = new BMap.Point(PlacerInfo.longitude, PlacerInfo.latitude);
	
map.centerAndZoom(point, 19);
map.enableScrollWheelZoom(); //开启鼠标滚轮缩放

map.enableContinuousZoom();

//http://www.intentplay.com/download/flag-icon.png
var falgIcon = new BMap.Icon("http://www.intentplay.com/download/flag_72px.png", new BMap.Size(72,72));
var qrcodeIcon = new BMap.Icon("http://www.intentplay.com/download/qrcode_32px.png", new BMap.Size(32,32));
var markerPlacer = new BMap.Marker(point,{icon:falgIcon});  // 创建标注

map.addOverlay(markerPlacer);
var label = new BMap.Label(PlacerInfo.name, {offset:new BMap.Size(20,-20)});
markerPlacer.setLabel(label); //添加百度label
//markerPlacer.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

var markerTemp = null
function showInfo(e){
	clearTempMarker();
	
	qrcoderPanel.longitude = e.point.lng;
	qrcoderPanel.latitude = e.point.lat;
	var pointTemp = e.point;
	markerTemp = new BMap.Marker(pointTemp);
	map.addOverlay(markerTemp);
	markerTemp.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}
map.addEventListener("click", showInfo);

function listQrcoderByPlacerId(callback) {
	console.log("listQrcoderByPlacerId:" + QrcoderParams.placeId);
	var baidumap = map;
	axios.get(URLs.qrcoderList, {
		params: QrcoderParams,
	  })
	  .then(function (response) {
	    if (response.status === 200 && response.data.code === 0) {
	    	var qrcoderList = response.data.data.rows;
	    	//console.log(qrcoderList);
	    	callback(qrcoderList);
	    } else {
	    	console.log(response.data.code);
	    }
	  })
	  .catch(function (error) {
	    console.log(error);
	  });
}

listQrcoderByPlacerId(function(qrcoderList) {
	qrcoderPanel.qrcoders = qrcoderList;
	for (var no in qrcoderList) {
		var qmarker = buildQrcoderMarker(qrcoderList[no].longitude, qrcoderList[no].latitude);
		map.addOverlay(qmarker);
		//qmarker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	}
});

function buildQrcoderMarker(longitude, latitude) {
	var qpoint = new BMap.Point(longitude, latitude);
	console.log(qpoint);
	return new BMap.Marker(qpoint, {icon:qrcodeIcon})
}

function clearTempMarker() {
	if (markerTemp !== null) {
		map.removeOverlay(markerTemp);
	}
}
