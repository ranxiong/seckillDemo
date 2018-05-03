<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h2>${seckill.name}</h2>
        </div>
        <div class="panel-body">
            <h2 class="text_danger">
                <!--显示图标-->
                <span class="glyphicon glyphicon-time"></span>
                <!--展示倒计时-->
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
    <%--登录弹出层，输入电话--%>
    <div id="killPhoneModal" class="modal fade modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-cneter">
                    <span class="glyphicon glyphicon-phone"></span>秒杀电话：
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killPhone" id="killPhoneKey"
                               placeholder="填手机号^o^" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--验证信息--%>
                <span id="killPhoneMessage" class="glyphicon"></span>
                <button class="btn" type="button" id="killPhoneBtn">
                    <span class="glyphicon glyphicon-phone"></span>
                    Submit
                </button>
            </div>
        </div>
    </div>
</div>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- 使用CDN 获取公共js http://www.bootcdn.con/ -->
<!-- cdn.bootcss.com 弹窗 -->
<script src="//cdn.bootcss.com/bootstrap-modal/2.2.6/js/bootstrap-modalmanager.js"></script>
<!-- cdn.bootcss.com cookie -->
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- cdn.bootcss.com countDown倒计时插件 -->
<script src="//cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<%--开始编写交互逻辑--%>
<script src="/resources/script/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        seckill.detail.init({
            seckillId:${seckill.seckillId},
            startTime:${seckill.startTime.time},
            endTime:${seckill.endTime.time}
        });
    });
</script>
</html>