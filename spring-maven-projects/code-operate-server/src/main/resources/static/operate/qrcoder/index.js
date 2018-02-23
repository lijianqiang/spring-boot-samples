var URLs = {
		list: "/api/qrcoders",
		query: "/api/qrcoders",
		remove: "/api/qrcoders/"
	}


var qrcoderQueryFrom = new Vue({
	el: "#query-form",
	data: {
		provinceNo: null,
		cityNo: null,
		regionNo: null,
		name: null,
		placeId: null,
		page: 1,
		limit: 20
	},
	methods: {
		search: function() {
			qrcoderQueryFrom.page = 1;
			qrcoderListTable.getQrcoders();
		},
		reset: function() {
			this.provinceNo = null;
			this.cityNo = null;
			this.regionNo = null;
			this.name = null;
			this.placeId = null;
			this.page = 1;
			this.limit = 20;
			qrcoderListTable.init();
		},
		buildQueryParams: function() {
			var params = {};
			params.provinceNo = this.provinceNo;
			params.cityNo = this.cityNo;
			params.regionNo = this.regionNo;
			params.name = this.name;
			params.placeId = this.placeType;
			params.page = this.page;
			params.limit = this.limit;
			
			return params;
		}
	}
});

var qrcoderListTable = new Vue({
		el: "#qrcoder-table",
		data: {
			qrcoders: [],
		},
		methods: {
			init: function() {
				qrcoderQueryFrom.page = 1;
				this.getQrcoders();
			},
			getQrcoders: function() {
				console.log("getQrcoders:" + qrcoderQueryFrom.page);
				var that = this;
				axios.get(URLs.list, {
					params: qrcoderQueryFrom.buildQueryParams(),
				  })
				  .then(function (response) {
				    if (response.status === 200 && response.data.code === 0) {
				    	that.qrcoders = response.data.data.rows;
				    	pagination.setPageConfig(response.data.data.total, qrcoderQueryFrom.limit, qrcoderQueryFrom.page);
				    } else {
				    	console.log(response.data.code);
				    }
				  })
				  .catch(function (error) {
				    console.log(error);
				        mdui.snackbar({
						  message: error,
						  timeout: 2000
						});
				  });
			},
			deleteItem: function(id) {
				console.log("delete:" + id);
				var that = this;
				axios.delete('/qrcoder/' + id ).then(function(response) {
					if (response.status === 200 && response.data.code === 0) {
						that.getQrcoders(that.page);
				    } else {
				    	console.log(response.data.code);
				    	mdui.snackbar({
							  message: "delete failed",
							  timeout: 2000
							});
				    }
				}).catch(function(error) {
					mdui.snackbar({
						  message: error,
						  timeout: 2000
						});
				});
			},
			viewItem: function(id) {
				console.log("view:" + id);
			},
			updateItem: function(id) {
				console.log("update:" + id);
				this.sayHello();
			},
			sayHello: function() {
				console.log("self sayHello");
			}
		}
});

var pagination = new Vue({
	el: "#pagination",
	data: {
		total: 0,
		limit: 0,
		page: 0,
		isCanPre: false,
		isCanNext: false
	},
	methods: {
		setPageConfig: function(total, limit, page) {
			this.total = total;
			this.limit = limit;
			this.page = page;
			console.log("total:" + total);
			console.log("limit:" + limit);
			console.log("page:" + page);
			var totalPage = total == 0 ? 0 : Math.ceil(total/limit);
			this.isCanPre = page > 1;
			this.isCanNext = page < totalPage;
		},
		previous: function() {
			if (this.isCanPre == false) {
				return;
			}
			qrcoderQueryFrom.page = qrcoderQueryFrom.page + 1;
			qrcoderListTable.getQrcoders();
		},
		next: function() {
			if (this.isCanNext == false) {
				return;
			}
			qrcoderQueryFrom.page = qrcoderQueryFrom.page + 1;
			qrcoderListTable.getQrcoders();
		}
	}
});


	
qrcoderListTable.init();