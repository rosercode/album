var mainData = {
    pictureList:[]
}
new Vue({
    el: "#main",
    data: mainData,
    created: function () {
        this.hello3()
    },
    updated: function () {

    },
    methods: {
        // 获取照片列表
        hello3: function () {
            axios.get('/picture/queryAll?source=2')
                .then(function (response) {
                    // 处理成功情况
                    // console.log(response);
                    const res = response.data
                    mainData.pictureList = res.data
                    alertify.notify("照片列表获取成功", 'success', 3, function () {
                    });
                })
                .catch(function (error) {
                    // 处理错误情况
                    alertify.notify("照片列表获取失败" + error, 'success', 3, function () {
                    });
                    // console.log(error);
                })
                .then(function () {
                    // 总是会执行
                });
        },
        // 删除图片信息
        deletePicture: function (photoId) {
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