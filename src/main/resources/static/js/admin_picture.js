var mainData = {

    pictureList:[]

}
new Vue({
    el:"#main",
    data:mainData,
    created:function () {
        this.hello3()
    },
    updated:function () {

    },
    methods:{
        hello3:function () {
            axios.get('/picture/queryAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    const res = response.data
                    mainData.pictureList = res.data
                    alertify.notify("照片列表获取成功", 'success', 3, function () {});
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("照片列表获取失败" + error, 'success', 3, function () {});
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        // 删除图片信息
        deletePicture:function (photoId) {
            console.log(photoId)
            const that = this
            axios.get('/picture/delete?photoId=' + photoId)
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    that.$options.methods.hello3()
                    alertify.notify("相册删除完成", 'success', 3, function () {
                    });
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("相册删除失败" + error, 'success', 3, function () {
                    });
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        // 更新图片信息
        updatePicture:function (photoId) {
            const that = this
            axios.get('/picture/updateStatus?photoId='+photoId)
                .then(function (response) {
                    // 处理成功情况
                    that.$options.methods.hello3()
                    alertify.notify("相册状态更新完成", 'success', 3, function () {});
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("相册状态更新失败" + error, 'success', 3, function () {});
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },

    }
})