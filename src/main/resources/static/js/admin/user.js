import * as obj from "/js/common.js";

const common = obj.common

var mainData = {
    userList:[],
    page: {
        totalNum: 100,  // 数据总数
        totalPage: 1,  // 总页数
        currentPage: 3,  // 当前页
        pageSize: 39  // 页大小
    }

}
new Vue({
    el: "#main",
    data: mainData,
    created: function () {
        this.hello1()
    },
    updated: function () {

    },
    methods: {
        hello1: function () {
            axios.get('/user/findAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    const res = response
                    mainData.userList = res.data
                    alertify.notify("用户列表获取成功", 'success', 3, function () {
                    });
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("用户列表获取失败" + error, 'success', 3, function () {
                    });
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        // 删除用户信息
        deleteUser: function (userId) {
            common.deleteUser(userId,function (err, response){
                if (err){
                    alertify.notify("用户信息删除失败" + error, 'success', 3, function () {});
                }else{
                    that.$options.methods.hello1()
                    alertify.notify("用户信息删除成功", 'success', 3, function () {});
                }
            })
        }
    }
})

