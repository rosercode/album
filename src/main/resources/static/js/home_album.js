import * as obj from "/js/common.js";

const common = obj.common

var mainData = {
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
    data: mainData,
    created: function () {
        this.hello2()

    },
    updated: function () {

    },
    methods: {
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
        // 添加相册
        addAlbum: function () {
            const albumName = document.getElementById("inputAlbumTitle").value  // 相册名称
            const albumRight = document.getElementById("inputAlbumRight").value  // 相册是否公开
            let file = document.getElementById('inputFormImage1').files[0];

            // 构建 Post 请求
            // 请求体为 相册名称（字符串） + 相册封面
            const that = this
            let formData = new FormData();
            formData.append("imageFile", file , file.name); // 相册封面图片
            formData.append("albumName", albumName);   // 相册名称
            formData.append("albumRight", albumRight === '公开' ? 1 : 0); // 图片

            const config = {
                headers: {"Content-Type": "multipart/form-data;boundary=" + new Date().getTime()}
            };
            axios.post("/album/insert", formData, config)
                .then(function (response) {
                    console.log(response);
                    that.$options.methods.hello3()
                    alertify.notify("相册创建完成", 'success', 3, function () {
                        that.$options.methods.hello2()
                    });
                })
                .catch(function (error) {
                    alertify.notify("相册创建失败" + error, 'success', 3, function () {
                    });
                    console.log(error);
                });
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
        }
    }
})