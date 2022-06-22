var mainData = {
    userList:[]

}
new Vue({
    el:"#main",
    data:mainData,
    created:function () {

        this.hello1()

    },
    updated:function () {

    },
    methods:{
        hello1:function () {
            axios.get('/user/findAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    mainData.userList = response.data.data
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

    }
})