$(function(){
    //点击左侧导航栏
    $(".list-group-item").bind("click",function(){
        $(this).addClass("menuActive").siblings().removeClass("menuActive");
    });
    $(".leftMenu").hover(function(){
        $(".menuNarrow").removeClass("hidden");
    },function(){
        $(".menuNarrow").addClass("hidden");
    })

    //点击图片放大
    $(".formgroupImg").find("img").bind("click",function(){
        var _src = $(this).attr("src");
        $(".modalImg").html("<img src="+ _src +"/>");
        $('#ModalImg').modal();
        //图片放大模态框隐藏后触发的事件
        $("#ModalImg").on("hidden.bs.modal",function(e){
            $("body").addClass("modal-open")
        })
    })

    // 菜单权限设置
    $.ajax({
        type:"POST",
        url:"/iwuliu/welcomeManager/roleManager",
        success: function (data){
            var obj = eval(data);
            if(obj.result==1){
                $(".leftMenu").find(".hidden").removeClass("hidden")
            }
        },
        error:function(){
            return false;
        }
    })
})
//权限设置
function roleManager(){
    // 菜单权限设置
    $.ajax({
        type:"POST",
        url:"/iwuliu/welcomeManager/roleManager",
        success: function (data){
            var obj = eval(data);
            if(obj.result==1){
                $(".leftMenu").find(".hidden").removeClass("hidden")
            }
        },
        error:function(){
            return false;
        }
    })
}

//模态框点击全屏
function fullScreen(ele){
    var modalDialog = $(ele).parents(".modal-dialog");
    var isClick = $(ele).attr("isClick");
    switch (isClick){
        case "true":
            $(ele).attr("isClick","false");
            $(ele).find("span").removeClass("glyphicon-resize-full");
            $(ele).find("span").addClass("glyphicon-resize-small");
            modalDialog.css("width","100%");
            break;
        case "false":
            $(ele).attr("isClick","true");
            $(ele).find("span").removeClass("glyphicon-resize-small");
            $(ele).find("span").addClass("glyphicon-resize-full");
            modalDialog.css("width","70%");
            break;
        default:
            break;
    }
}

//点击导航栏宽度变化
function menuNarrow(ele){
    var _isClick = $(ele).attr("isClick");
    if(_isClick == "true"){
        $(ele).attr("isClick","false")
        $(ele).parents(".leftMenu").addClass("menu-min");
        $(".iframeMain").addClass("iframeMainNarrow")
    }else{
        $(ele).attr("isClick","true")
        $(ele).parents(".leftMenu").removeClass("menu-min");
        $(".iframeMain").removeClass("iframeMainNarrow")
    }
}
//下拉菜单
function customDropDown(ele){
    this.dropdown=ele;
    this.placeholder=this.dropdown.find(".placeHolder");
    this.options=this.dropdown.find("ul.dropdown-menu > li");
    this.val='';
    this.index=-1;//默认为-1;
    this.initEvents();
}
customDropDown.prototype={
    initEvents:function(){
        var obj=this;
        //这个方法可以不写，因为点击事件被Bootstrap本身就捕获了，显示下面下拉列表
        obj.dropdown.on("click",function(event){
            $(this).toggleClass("active");
        });

        //点击下拉列表的选项
        obj.options.on("click",function(){
            var opt=$(this);
            obj.text=opt.find("a").text();
            obj.val=opt.attr("value");
            obj.dropdown.find(".placeHolder").attr("value",obj.val)
            obj.index=opt.index();
            obj.placeholder.text(obj.text);
        });
    },
    getText:function(){
        return this.text;
    },
    getValue:function(){
        return this.val;
    },
    getIndex:function(){
        return this.index;
    }
}

//点击编辑按钮
function detail_edit(ele){
    var modalContent = $(ele).parents(".modal-content");
    var uneditable = modalContent.find(".uneditable");
    var docubleUneditable = modalContent.find(".docubleUneditable");
    var _isClick = $(ele).attr("isClick");
    if(_isClick == "true"){
        $(ele).attr("isClick","false");
        $(ele).css("background","#eeeeee");
        $(ele).attr("disabled","true")
        uneditable.find("button").removeAttr("disabled");
        uneditable.find("input").removeAttr("disabled");
        docubleUneditable.find("button").attr("disabled","true")
        docubleUneditable.find("input").attr("disabled","true")
        uneditable.removeClass("uneditable");
        $(".elementDisplayNone").removeClass("elementDisplayNone")
    }
}

//删除数组中的制定值
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

//图片上传
function loadImg(ele) {
    $(ele).parents(".formgroupImg").find(".hidden").click();
    //点击图片放大
    $(".formgroupImg").find("img").bind("click", function() {
        var _src = $(this).attr("src");
        $(".modalImg").html("<img src=" + _src + "/>");
        $('#ModalImg').modal();
        //图片放大模态框隐藏后触发的事件
        $("#ModalImg").on("hidden.bs.modal", function(e) {
            $("body").addClass("modal-open")
        })
    })
}

//图片上传预览    IE是用了滤镜。
function previewImage(ele) {
    var MAXWIDTH  = 100;
    var MAXHEIGHT = 40;
    var imgSrc;
    if(ele.files && ele.files[0]) {
        $(ele).parents(".formgroupImg").find(".display_inlineBlcok").html('<img title="点击放大显示"/>')
        var img = $(ele).parents(".formgroupImg").find("img")[0];
        img.onload = function() {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width  =  rect.width;
            img.height =  rect.height;
        }
        var reader = new FileReader();
        reader.onload = function(evt) {
            img.src = evt.target.result;
            imgSrc = evt.target.result;
        }
        reader.readAsDataURL(ele.files[0]);
    }
    else {
        ele.blur();
        var sFilter = 'FILTER: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)';
        $(ele).parents(".formgroupImg").find(".display_inlineBlcok").html('<img title="点击放大显示"/>')
        var img = $(ele).parents(".formgroupImg").find("img")[0];
        var src = document.selection.createRange().text;
        img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=" + src + ")";
        console.log(src)
        img.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;

    }
}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {
        top: 0,
        left: 0,
        width: width,
        height: height
    };
    if(width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if(rateWidth > rateHeight) {
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        } else   {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}