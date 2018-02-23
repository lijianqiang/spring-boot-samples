var inst = new mdui.Dialog('#qrcode-dialog', {modal:true});

var URLs = {
		list: "/api/qrcoders",
		query: "/api/qrcoders",
		remove: "/api/qrcoders/"
	}

var menu = new Vue({
	el: "#menu-list",
	methods: {
		showNewQrcode: function() {
			console.log("show qrcode-form");
			inst.open();
		}
	}
});

var qrcodeCreateForm = new Vue({
	el: "#qrcode-form",
	data: {
		active: false,
		loginid: null,
		name: null,
		email: null,
		mobile:null
	},
	methods: {
		cancle: function() {
			console.log("close qrcode-form");
			inst.close();
		},
		confirm: function() {
			console.log("submit qrcode-form");
			this.submitUserForm();
		},
		submitUserForm: function() {
			this.active = true;
			var that = this;
			axios.post(URLs.list, {
				loginid: this.loginid,
				name: this.name,
				email: this.email,
				mobile: this.mobile
			  })
			  .then(function (response) {
			    console.log(response);
			    qrcodeListTable.init();
			    var mills = 2000 - new Date().getTime();
			    mills = mills > 0 ? mills : 1000;
			    if (response.status === 200 && response.data.code === 0) {
			    	setTimeout(function() {
			    		that.active = false;
			    		//that.showProcess("ok", mills);
			    		that.cancle();
						}, mills);
			    } else {
			    	setTimeout(function() {
			    		that.active = false;
			    		//that.showProcess(response.data.msg, mills);
			    		that.cancle();
						}, mills);
			    }
			  })
			  .catch(function (error) {
			    console.log(error);
			    var mills = 2000 - new Date().getTime();
			    mills = mills > 0 ? mills : 1000;
		    	setTimeout(function() {
		    		that.active = false;
		    		//that.showProcess(error, mills);
		    		that.cancle();
					}, mills);
			  });
		},
		showProcess: function(msg, millSeconds) {
			mdui.snackbar({
				  message: msg,
				  timeout: millSeconds
				});
		}
	}
	
});

var qrcodeQueryFrom = new Vue({
	el: "#query-form",
	data: {
		loginid: null,
		name: null,
		email: null,
		mobile:null
	},
	methods: {
		search: function() {
			qrcodeListTable.getQrcodes(1);
		},
		reset: function() {
			this.loginid = null;
			this.name = null;
			this.email = null;
			this.mobile = null;
			qrcodeListTable.init();
		}
	}
	
});

var qrcodeListTable = new Vue({
		el: "#qrcode-table",
		data: {
			qrcodes: [],
			page: 1,
		},
		methods: {
			init: function() {
				this.getQrcodes(1);
			},
			getQrcodes: function(pageNo) {
				console.log("getQrcodes:" + pageNo);
				this.page = pageNo;
				var that = this;
				axios.get(URLs.list, {
					params:{page: pageNo, loginid:qrcodeQueryFrom.loginid, name: qrcodeQueryFrom.name, email:qrcodeQueryFrom.email, mobile:qrcodeQueryFrom.mobile},
				  })
				  .then(function (response) {
				    if (response.status === 200 && response.data.code === 0) {
				    	that.qrcodes = response.data.data.rows;
				    	pagination.setPageConfig(response.data.data.total, 20, response.data.data.page);
				    	mdui.updateTables("#qrcode-table");
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
				axios.delete('/qrcode/' + id ).then(function(response) {
					if (response.status === 200 && response.data.code === 0) {
						that.getQrcodes(that.page);
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
			var totalPage = total == 0 ? 0 : Math.ceil(total/limit);
			this.isCanPre = page != 1;
			this.isCanNext = page < totalPage;
		},
		previous: function() {
			if (this.isCanPre == false) {
				return;
			}
			qrcodeListTable.getQrcodes(qrcodeListTable.page - 1);
		},
		next: function() {
			if (this.isCanNext == false) {
				return;
			}
			qrcodeListTable.getQrcodes(qrcodeListTable.page + 1);
		}
	}
});


	
qrcodeListTable.init();