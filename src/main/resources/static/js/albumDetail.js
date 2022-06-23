import * as obj from "/js/common.js";
const common = obj.common

// 解析 URL，获取 URL 中的指定的参数对应的值
function getUrlParam(urlStr, urlKey) {
    const url = new URL(urlStr)
    var reg = new RegExp('[\?\&]' + urlKey + '=([^\&]*)(\&?)', 'i')
    var r = url.search.match(reg)
    return r ? r[1] : ''
}

var mainVue = {
    imageList:[],  // 图片列表
    remarkList:[]  // 评论列表
}
new Vue({
    el:"#main",
    created:function (){
        const albumId = getUrlParam(window.location.href,"albumId")
        // 获取图片列表
        common.findAllPicture({source:1,albumId:albumId},function (err, response) {
            if (err){

            }else{
                const res = response.data
                mainVue.imageList = res.data
                alertify.notify("图片列表获取成功", 'success', 3, function () {});
            }


        })
        // 评论列表列表
        common.findAllRemark({source:1,albumId:albumId},function (err, response) {
            if (err){
                alertify.notify("评论列表获取失败" + error, 'success', 3, function () {});
            }else{
                mainVue.remarkList = response.data.data
                alertify.notify("评论列表列表获取成功，一共"+mainVue.remarkList.length+"条评论", 'success', 3, function () {});
            }

        })
    },
    updated:function () {

    },
    data:mainVue,
    methods: {
        // 发布评论
        expressComment: function () {
            const commentContent = document.getElementById("comment").value
            axios.get(
                '/remark/expressRemark', {
                    params: {
                        albumId: getUrlParam(window.location.href, "albumId"),
                        remarkInfo: commentContent
                    }
                })
                .then(function (response) {
                    // 处理成功情况
                    console.log(response.data);
                    const data = response.data
                    if (data['code'] == 1) {
                        alertify.notify(response.data.message, 'success', 3, function () {
                        });
                    } else if (data['code'] == -2) {
                        alertify.notify(response.data.message + "，三秒后跳转到到登录界面", 'success', 3, function () {

                        });
                    } else {
                        alertify.notify(response.data.message, 'success', 3, function () {
                        });
                    }
                    document.getElementById("comment").value = ""
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify(response.data.message + error, 'success', 3, function () {
                    });
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        }
    }
})