//存放主要交互逻辑js代码
//javascript 模块化
var seckill = {

    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    handleSeckill: function (seckillId, node) {//处理秒杀
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id = "killBtn">开始秒杀</button>')
            .show();
        //方式：post
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //在回调函数中，执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开始秒杀
                    //获取秒杀地址
                    var killUrl = seckill.URL.execution(seckillId, exposer['md5']);
                    //console.log('killUrl:' + killUrl);
                    //绑定一次点击事件,排除用户无脑点击秒杀按钮
                    $('#killBtn').one('click', function () {
                        //执行秒杀请求
                        //1.先禁用按钮
                        $('#killBtn').addClass('disabled');
                        //2.发送秒杀请求
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var seckillResult = result['data'];
                                console.log('killurl:' + killUrl);
                                var state = seckillResult['state'];
                                var stateInfo = seckillResult['stateInfo'];
                                //3.显示秒杀结果
                                node.hide()
                                    .html('<label class="label label-success">' + stateInfo + '</label>')
                                    .show();

                            }
                        });
                    });
                } else {
                    //未开启秒杀
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    //重新计算计时
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result:' + result);
            }
            ;
        });
    },

    validatePhone: function (phone) {//验证手机号
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countDown: function (seckillId, nowTime, startTime, endTime) {//获取秒杀地址，控制现实逻辑，执行秒杀
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束！');
        } else if (nowTime < startTime) {
            //秒杀未开始,计时
            var killTime = new Date(startTime + 1000);
            //利用时间插件 输出倒计时
            seckillBox.countdown(killTime, function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countDown', function () {//倒计时结束，添加事件
                //获取秒杀地址，控制现实逻辑，执行秒杀
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },

    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //用户手机验证和登录,计时交互
            //规划我们的交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            //验证手机号.
            if (!seckill.validatePhone(killPhone)) {
                // 绑定phone
                // 控制输出
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({//bootstrap modal插件
                    show: true,//显示弹出层
                    backrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    // console.log('inputPhone='+inputPhone);//TODO 调试后取消打印
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage')
                            .hide()
                            .html('<label class="label label-danger">手机号错误！</label>')
                            .show(300);
                    }
                });
            }
            //已经登录:
            //计时交互 ajax获取时间 ,方式：get
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result : ' + result);
                }
            });
        }
    }
}