var mainData = {
    albumList:[]
}
new Vue({
    el:"#main",
    data:mainData,
    created:function () {
        this.hello4()

    },
    updated:function () {

    },
    methods:{
        hello4:function () {
            axios.get('/remark/queryAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    mainData.remarkList = response.data.data
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
        // 更新评论信息
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