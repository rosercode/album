var mainVue = {
    albumList:[]
}
new Vue({
    el:"#main",
    created:function (){
        axios.get('/album/queryAll?source=1')
            .then(function (response) {
                // 处理成功情况
                // console.log(response);
                // const data = response.data
                mainVue.albumList = response.data
                alertify.notify("公开的相册列表获取成功", 'success', 3, function () {});
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
    updated:function () {

    },
    data:mainVue,
    methods:{

    }
})