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
            console.log('[x] 创建相册')
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
                    alertify.notify("相册创建完成", 'success', 3, function () {
                        that.$options.methods.hello2()
                    });
                })
                .catch(function (error) {
                    alertify.notify("相册创建失败" + error, 'success', 3, function () {
                    });
                    console.log(error);
                });
        }
    }
})