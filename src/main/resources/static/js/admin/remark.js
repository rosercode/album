import * as obj from "/js/common.js";

const common = obj.common

var mainData = {
    remarkList:[],
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
            const that = this
            common.findAllRemark({source: 2}, function (err, response) {
                if (err) {
                    alertify.notify("评论列表获取失败" + error, 'success', 3, function () {});
                } else {
                    mainData.remarkList = response.data.data
                    alertify.notify("评论列表获取成功", 'success', 3, function () {});
                }

            })
        },
        // 删除评论信息
        deleteRemark: function (remarkId) {
            const that = this
            common.deleteRemark(remarkId, function (response){
                that.$options.methods.hello()
                alertify.notify("评论删除完成", 'success', 3, function () {});
            })
        },
        // 更新评论信息
        updateRemark: function (remarkId){
            const that = this
            common.updateRemark(remarkId, function (err, response) {
                if (err) {
                    alertify.notify("相册状态更新失败" + error, 'success', 3, function () {});
                }else{
                    that.$options.methods.hello()
                    alertify.notify("相册状态更新完成", 'success', 3, function () {});
                }
            })
        }
    }
})