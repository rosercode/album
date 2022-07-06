import * as obj from "/js/common.js";
const common = obj.common

var mainData = {
    albumList:[],
    pictureList: [],
    album:{},
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
        this.hello2()
        this.hello3()
    },
    updated: function () {

    },
    methods: {
        // 获取照片列表
        hello3: function () {

            common.findAllPicture({source:2},function (err, response) {
                if (err){
                    alertify.notify("照片列表获取失败" + error, 'success', 3, function () {});
                }else{
                    const res = response.data
                    mainData.pictureList = res.data
                    alertify.notify("照片列表获取成功", 'success', 3, function () {});
                }
            })
        },
        // 获取相册列表
        hello2: function () {
            common.findAllAlbum({source:2},function (err, response){
                if (err){
                    alertify.notify("相册列表获取失败" + error, 'success', 3, function () {});

                }else{
                    mainData.albumList = response.data.data
                    alertify.notify("相册列表获取成功", 'success', 3, function () {});
                }
            })
        },
        // 相册添加图片
        addPhoto: function () {
            const that = this
            let file = document.getElementById('inputFormControlFile1').files[0];

            let formData = new FormData();
            formData.append("imageFile", file, file.name);
            // console.log($("#inputPhotoAlbum").val())
            formData.append("albumId", $("#inputPhotoAlbum").val());   // 图片名称
            formData.append("photoIntro", $("#inputPhotoDesc").val()); // 图片描述
            formData.append("photoRight", $("#inputPhotoRight").val()); // 图片描述

            const config = {
                headers: {"Content-Type": "multipart/form-data;boundary=" + new Date().getTime()}
            };

            axios.post("/picture/insert", formData, config)
                .then(function (response) {
                    console.log(response);
                    that.$options.methods.hello3()
                    alertify.notify("图片上传完成", 'success', 3, function () {
                    });
                })
                .catch(function (error) {
                    alertify.notify("图片上传失败", 'success', 3, function () {
                    });
                    console.log(error);
                });
        },
        // 删除图片信息
        deletePicture: function (photoId) {
            const that = this
            common.deletePicture(photoId, function (err, response) {
                if (err) {
                    alertify.notify("相册删除失败" + response, 'success', 3, function () {});
                } else {
                    that.$options.methods.hello3()
                    alertify.notify("相册删除完成", 'success', 3, function () {});
                }
            })
        },
    }
})