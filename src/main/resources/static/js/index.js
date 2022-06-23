import * as obj from "/js/common.js";
const common = obj.common

var mainVue = {
    albumList:[],
    page: {
        totalNum: 100,  // 数据总数
        totalPage: 1,  // 总页数
        currentPage: 3,  // 当前页
        pageSize: 39  // 页大小
    }
}
new Vue({
    el: "#main",
    data: mainVue,
    created: function () {
        common.findAllAlbum({source:1},function (err, response){
            if (err){

            }else{
                mainVue.albumList = response.data.data
                alertify.notify("公开的相册列表获取成功", 'success', 3, function () {});
            }

        })
    },
    updated: function () {

    },
    methods: {}
})