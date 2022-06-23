import * as obj from "/js/common.js";

const common = obj.common


var mainData = {
    pictureList:[],
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
        this.hello()
    },
    updated:function () {

    },
    methods:{
        hello:function () {
            common.findAllPicture({source:2},function (err, response) {
                if (err){
                    alertify.notify("照片列表获取失败" + error, 'success', 3, function () {});
                }else{
                    const res = response.data
                    mainData.pictureList = res.data
                    alertify.notify("照片列表获取成功", 'success', 3, function () {});
                }
            })
        },
        // 删除图片信息
        deletePicture: function (photoId) {
            const that = this
            common.deletePicture(photoId, function (err, response) {
                if (err) {
                    alertify.notify("相册删除失败" + error, 'success', 3, function () {});

                } else {
                    that.$options.methods.hello()
                    alertify.notify("相册删除完成", 'success', 3, function () {});
                }
            })
        },
        // 更新图片信息
        updatePicture: function (pictureId) {
            const that = this
            common.updatePicture(pictureId, function (err , response) {
                if (err) {
                    alertify.notify("相册状态更新失败" + error, 'success', 3, function () {});
                } else {
                    that.$options.methods.hello()
                    alertify.notify("相册状态更新完成", 'success', 3, function () {});
                }
            })
        }
    }
})