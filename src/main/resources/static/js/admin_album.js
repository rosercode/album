import * as obj from "/js/common.js";

const common = obj.common


var mainData = {
    albumList:[],
    page: {
        totalNum: 100,  // 数据总数
        totalPage: 1,  // 总页数
        currentPage: 3,  // 当前页
        pageSize: 39  // 页大小
    }
}
new Vue({
    el:"#main",
    data:mainData,
    created:function () {
        this.hello();
    },
    updated:function () {

    },
    methods:{
        hello: function () {
            common.findAllAlbum({source:2},function (err, response){
                if (err){
                    alertify.notify("相册列表获取失败" + error, 'success', 3, function () {});

                }else{
                    mainData.albumList = response.data.data
                    alertify.notify("相册列表获取成功", 'success', 3, function () {});
                }

            })
        },

        // 删除相册信息
        deleteAlbum: function (albumId) {
            const that = this
            common.deleteAlbum(albumId, function (err, response) {
                if (err){
                    alertify.notify("相册删除失败" + error, 'success', 3, function () {});
                }else{
                    that.$options.methods.hello()
                    alertify.notify("相册删除完成", 'success', 3, function () {});
                }
            })
        },

        // 管理员审核相册信息
        updateAlbum: function (albumId) {
            const that = this
            common.updateAlbum(albumId, function (response) {
                that.$options.methods.hello()
                alertify.notify("相册状态更新完成", 'success', 3, function () {});
            })
        }
    }
})