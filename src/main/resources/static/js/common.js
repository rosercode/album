// 这个模块依赖于 Axios
// 是对 Axios 的封装。目的：减少冗余代码（重复代码）。缺点：配置不够灵活
export const common = {

    // 删除用户信息
    deleteUser: function (userId, callback) {
        console.log(userId)
        axios.get('/user/delete?userId=' + userId)
            .then(function (response) {
                // 处理成功情况
                // console.log(response);
                callback(false, response)
            })
            .catch(function (error) {
                callback(true, error)
                // 处理错误情况
                // console.log(error);
            })
            .then(function () {
                // 总是会执行
            });
    },

    // 获取所有的评论信息
    findAllRemark:function (param, callback){
        axios.get('/remark/queryAll',{params:param})
            .then(function (response) {
                // 处理成功情况
                // console.log(response);
                callback(false, response)
            })
            .catch(function (error) {
                // 处理错误情况
                callback(true, error)
                // console.log(error);
            })
            .then(function () {
                // 总是会执行
            });
    },
    // 删除评论信息
    deleteRemark: function (remarkId, callback) {
        const that = this
        axios.get('/remark/delete?remarkId=' + remarkId)
            .then(function (response) {
                // 处理成功情况
                callback(response)
                // console.log(response);

            })
            .catch(function (error) {
                // 处理错误情况
                alertify.notify("评论删除失败" + error, 'success', 3, function () {
                });
                // console.log(error);
            })
            .then(function () {
                // 总是会执行
            });
    },
    // 更新评论信息
    updateRemark: function (remarkId, callback) {
        axios.get('/remark/updateStatus?remarkId=' + remarkId)
            .then(function (response) {
                // 处理成功情况
                callback(false, response)
            })
            .catch(function (error) {
                callback(false, error)
            })
            .then(function () {
                // 总是会执行
            });
    },

    // 获取所有的的照片信息
    findAllPicture:function (param, callback){
        axios.get('/picture/queryAll',{params:param})
            .then(function (response) {
                // 处理成功情况
                // console.log(response);
                callback(false, response)
            })
            .catch(function (error) {
                callback(true, error)
            })
            .then(function () {
                // 总是会执行
            });
    },
    // 删除图片信息
    deletePicture: function (photoId, callback) {
        axios.get('/picture/delete?photoId=' + photoId)
            .then(function (response) {
                // 处理成功情况
                // console.log(response);
                callback(false, response)
            })
            .catch(function (error) {
                // 处理错误情况
                callback(true, error)
                // console.log(error);
            })
            .then(function () {
                // 总是会执行
            });
    },

    // 更新图片信息
    updatePicture: function (photoId, callback) {
        axios.get('/picture/updateStatus?photoId=' + photoId)
            .then(function (response) {
                // 处理成功情况
                callback(false, callback)
            })
            .catch(function (error) {
                // 处理错误情况
                callback(true, callback)
            })
            .then(function () {
                // 总是会执行
            });
    },

    // 获取相册列表
    findAllAlbum:function (param, callback) {
        axios.get('/album/queryAll', {
            params: param
        }).then(function (response) {
            // 处理成功情况
            callback(false, response)
        }).catch(function (error) {
            // 处理错误情况
            callback(true, error)

        })
            .then(function () {
                // 总是会执行
            });
    },
    // 删除相册信息
    deleteAlbum: function (albumId, callback) {
        const that = this
        axios.get('/album/delete?albumId=' + albumId)
            .then(function (response) {
                callback(false, response)
            })
            .catch(function (error) {
                // 处理错误情况
                callback(true, error)
                // console.log(error);
            })
            .then(function () {
                // 总是会执行
            });
    },

    // 管理员审核相册信息
    updateAlbum: function (albumId,callback) {
        const that = this;
        axios.get('/album/verifyAlbum?albumId=' + albumId)
            .then(function (response) {
                // 处理成功情况
                callback(response)

            })
            .catch(function (error) {
                // 处理错误情况
                alertify.notify("相册状态更新失败" + error, 'success', 3, function () {
                });
                // console.log(error);
            })
            .then(function () {
                // 总是会执行
            });
    },
}