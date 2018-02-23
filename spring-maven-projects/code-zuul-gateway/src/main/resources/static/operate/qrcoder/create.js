var panelHandler = new mdui.Panel('#qrcoder-panel');

var URLs = {
    create: "/api/qrcoders"
}

var Placer = {
	longitude: document.getElementById("placer-longitude").value,
	latitude: document.getElementById("placer-latitude").value,
	name: document.getElementById("placer-name").value,	
	id: document.getElementById("placer-id").value
}

var qrcoderPanel = new Vue({
	el: "#qrcoder-panel",
	data: {
		name: Placer.name + "xxx的二维码",
		latitude: null,
		longitude: null,
        enabled: false,
        indoor: false,
        placerId: Placer.id
	},
	methods: {
		submitQrcoderForm: function() {
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
			params.latitude = this.latitude;
			params.longitude = this.longitude;
			params.name = this.name;
			params.enabled = this.enabled;
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
		closeQrcoderInfo: function() {
			panelHandler.close("#qrcoder-info-item");
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
	var isOk = qrcoderPanel.checkParams();
	console.log("onClickSubmit:" + isOk);
	if (isOk !== true) {
		return;
	}
	qrcoderPanel.submitQrcoderForm();
}

function onClickBack() {
	window.location.href = "/qrcoder/index.html";
}


//百度地图API功能
var map = new BMap.Map("allmap", {enableMapClick:false});//构造底图时，关闭底图可点功能
var point = new BMap.Point(Placer.longitude, Placer.latitude);
	
map.centerAndZoom(point, 18);
//map.enableScrollWheelZoom(); //开启鼠标滚轮缩放

map.enableContinuousZoom();

var marker = new BMap.Marker(point);
map.addOverlay(marker);
console.log("name:" + Placer.name);
var label = new BMap.Label(Placer.name, {offset:new BMap.Size(20,-20)});
marker.setLabel(label); //添加百度label


//坐标转换完之后的回调函数
var translateCallback = function (data){
  if(data.status === 0) {
    var marker = new BMap.Marker(data.points[0]);
    map.addOverlay(marker);
    var label = new BMap.Label("xxxxxx", {offset:new BMap.Size(20,-10)});
    marker.setLabel(label); //添加百度label
    map.setCenter(data.points[0]);
  }
}

var convertor = new BMap.Convertor();
var pointArr = [];
pointArr.push(point);
convertor.translate(pointArr, 1, 5, translateCallback)


var geoc = new BMap.Geocoder();
function showInfo(e){
	qrcoderPanel.longitude = e.point.lng;
	qrcoderPanel.latitude = e.point.lat;
}
map.addEventListener("click", showInfo);

