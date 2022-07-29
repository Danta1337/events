// 断言某个元素的value不能为空
function isNotBlank (id) {
    let element = document.getElementById(id);
    if (element == null) {
        mdui.alert("元素不存在诶...");
        return false;
    }
    let value = element.value;
    if (value === '') {
        mdui.alert(id + " can not be blank!");
        return false;
    }
    return true;
}

// 根据id获取某个元素的value值
function getElementValue(id) {
    return document.getElementById(id).value;
}

function setElementSrc(id, src) {
    document.getElementById(id).src = src;
}

function setElementValue(id, value) {
    document.getElementById(id).value = value;
}

function setElementInnerText(id, innerText) {
    document.getElementById(id).innerText = innerText;
}

function setElementInnerHtml(id, innerHtml) {
    document.getElementById(id).innerHTML = innerHtml;
}

function setElementAttribute(id, key, value) {
    document.getElementById(id).setAttribute(key, value)
}

// 根据元素idList拼凑GET请求链接的参数
function getGetParams(list) {
    if (list == null || list.length === 0) {
        return "";
    }
    let params = "?";
    for (let i = 0; i < list.length; i++) {
        params = params + list[i] + "=" + getElementValue(list[i]) + "&";
    }
    params = params.substring(0, params.length - 1);
    return params;
}

// 根据元素idList获取POST请求体中未JSON化的对象
function getPostBody(list) {
    if (list == null || list.length === 0) {
        return "";
    }
    let map = new Map();
    for (let i = 0; i < list.length; i++) {
        map.set(list[i], getElementValue(list[i]));
    }
    return map;
}

//
function checkResult(json) {
    if (json == null) {
        mdui.alert("服务端返回了空对象，寄！")
    }
    if (json.code !== '0') {
        mdui.alert(json.message);
        return false;
    }
    return true;
}

// 初始化appbar，可指定打开的list和激活的item
function initDrawerContent(content, list, item) {
    fetch("/static/html/" + content + ".html").then(response => response.text()).then(text => {
        document.getElementById("drawer-content").outerHTML = text;
        if (list != null) {
            document.getElementById(list).classList.add("mdui-collapse-item-open");
        }
        if (item != null) {
            document.getElementById(item).classList.add("mdui-list-item-active");
        }
        mdui.mutation();
    });
}

// 初始化appbar，可指定headline和title
function initAppbarContent(content, headline, title) {
    fetch("/static/html/" + content + ".html").then(response => response.text()).then(text => {
        document.getElementById(content).outerHTML = text;
        // 指定当前appbar的主题
        if (headline != null) {
            document.getElementById("appbar-headline").innerText = headline;
        }
        // 指定当前appbar的副标题
        if (title != null) {
            document.getElementById("appbar-title").innerText = title;
        }
        mdui.mutation();
    });
}

// 用于appbar的菜单图标操作左侧抽屉烂
function handleDrawer() {
    let instance = new mdui.Drawer('#drawer');
    instance.options.swipe = true;
    let state = instance.getState();
    if (state === 'opening' || state === 'opened') {
        instance.close();
    } else {
        instance.open();
    }
}