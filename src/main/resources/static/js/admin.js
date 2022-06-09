var mainData = {
    userList:[],
    albumList:[],
    pictureList:[],
    remarkList:[]
}
new Vue({
    el:"#main",
    data:mainData,
    created:function () {

        this.hello1()
        this.hello2()
        this.hello3()
        this.hello4()

    },
    updated:function () {

    },
    methods:{
        hello1:function () {
            axios.get('/user/findAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    mainData.userList = response.data
                    alertify.notify("用户列表获取成功", 'success', 3, function () {});
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("用户列表获取失败" + error, 'success', 3, function () {});
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        hello2:function () {
            axios.get('/album/queryAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    mainData.albumList = response.data
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
        hello4:function () {
            axios.get('/remark/queryAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    mainData.remarkList = response.data
                    alertify.notify("评论列表获取成功", 'success', 3, function () {});
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("评论列表获取失败" + error, 'success', 3, function () {});
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        // 删除用户信息
        deleteUser:function (userId) {
            const that = this
            console.log(userId)
            axios.get('/user/delete?userId='+userId)
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    that.$options.methods.hello1()
                    alertify.notify("用户信息删除成功", 'success', 3, function () {});
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("用户信息删除失败" + error, 'success', 3, function () {});
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
        // 更新相册信息
        updateAlbum:function (albumId) {
            const that = this;
            axios.get('/album/updateStatus?albumId='+albumId)
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
        // 删除评论信息
        deleteRemark:function (remarkId) {
            const that = this
            axios.get('/remark/delete?remarkId=' + remarkId)
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    that.$options.methods.hello4()
                    alertify.notify("评论删除完成", 'success', 3, function () {
                    });
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("评论删除失败" + error, 'success', 3, function () {
                    });
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        // 跟新评论信息
        updateRemark:function (remarkId) {
            const that = this
            axios.get('/remark/updateStatus?remarkId='+remarkId)
                .then(function (response) {
                    // 处理成功情况
                    that.$options.methods.hello4()
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