
var URLs = {
		list: "/api/placers",
		query: "/api/placers",
		remove: "/api/placers/"
}

var placerQueryFrom = new Vue({
	el: "#query-form",
	data: {
		provinceNo: null,
		provinceName: null,
		cityNo: null,
		cityName: null,
		regionNo: null,
		regionName: null,
		name: null,
		address: null,
		placeType: null,
		page: 1,
		limit: 20
	},
	methods: {
		search: function() {
			placerListTable.getPlacers(1);
		},
		reset: function() {
			this.provinceNo = null;
			this.cityNo = null;
			this.regionNo = null;
			this.name = null;
			this.placeType = null;
			this.page = 1;
			this.limit = 20;
			placerListTable.init();
		},
		buildQueryParams: function() {
			var params = {};
			params.provinceNo = this.provinceNo;
			params.cityNo = this.cityNo;
			params.regionNo = this.regionNo;
			params.name = this.name;
			params.placeType = this.placeType;
			params.page = this.page;
			params.limit = this.limit;
			
			return params;
		}
	}
	
});

var placerListTable = new Vue({
		el: "#placer-table",
		data: {
			placers: [],
		},
		methods: {
			init: function() {
				placerQueryFrom.page = 1;
				this.getPlacers();
			},
			getPlacers: function() {
				console.log("getPlacers:" + placerQueryFrom.page);
				var that = this;
				axios.get(URLs.list, {
					params: placerQueryFrom.buildQueryParams(),
				  })
				  .then(function (response) {
				    if (response.status === 200 && response.data.code === 0) {
				    	that.placers = response.data.data.rows;
				    	pagination.setPageConfig(response.data.data.total, placerQueryFrom.limit, placerQueryFrom.page);
				    	mdui.updateTables("#placer-table");
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
			deleteItemConfirm: function(id, name) {
				var that = this;
				mdui.confirm("请确认是否删除" + name + "?", "确认删除", function(){
					that.deleteItem(id);
				});
			},
			deleteItem: function(id) {
				console.log("delete:" + id);
				var that = this;
				axios.delete(URLs.remove + id ).then(function(response) {
					if (response.status === 200 && response.data.code === 0) {
						that.getPlacers();
						mdui.alert('删除成功');
				    } else {
				    	console.log(response.data.code);
				    	mdui.alert('失败原因:' + response.data.message, '删除失败');
				    }
				}).catch(function(error) {
					mdui.alert('失败原因:' + error, '删除失败');
				});
			},
			viewItem: function(id) {
				console.log("viewItem:" + id);
				window.location.href = "/area/placers/" + id +"/plates";
			},
			updateItem: function(id) {
				console.log("updateItem:" + id);
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
			placerListTable.getPlacers(placerListTable.page - 1);
		},
		next: function() {
			if (this.isCanNext == false) {
				return;
			}
			placerListTable.getPlacers(placerListTable.page + 1);
		}
	}
});


	
placerListTable.init();