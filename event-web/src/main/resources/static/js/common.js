function checkResult (result) {
    if (result == null) {
        alert("服务器开小差了");
    } else if (result.code !== '0') {
        alert(result.message);
    }
}