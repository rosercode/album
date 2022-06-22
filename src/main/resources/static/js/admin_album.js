var mainData = {
    albumList:[]
}
new Vue({
    el:"#main",
    data:mainData,
    created:function () {
        this.hello2()
    },
    updated:function () {

    },
    methods:{
        hello2:function () {
            axios.get('/album/queryAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    mainData.albumList = response.data.data
                    alertify.notify("相册列表获取成功", 'success', 3, function () {});
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("相册列表获取失败" + error, 'success', 3, function () {});
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });

        },

        // 删除相册信息
        deleteAlbum:function (albumId){
            console.log(albumId)
            const that = this
            axios.get('/album/delete?albumId='+albumId)
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    that.$options.methods.hello2()
                    alertify.notify("相册删除完成", 'success', 3, function () {});
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("相册删除失败" + error, 'success', 3, function () {});
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        // 管理员审核相册信息
        updateAlbum:function (albumId) {
            const that = this;
            axios.get('/album/verifyAlbum?albumId='+albumId)
                .then(function (response) {
                    // 处理成功情况
                    that.$options.methods.hello2()
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