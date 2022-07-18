<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/8
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
</head>
<body>
<a href="test/ssm.html">测试SSM整合环境</a><br/>
<button id="btn1">Send [5,6,12] One</button><br/>
<button id="btn2">Send [5,6,12] Two</button><br/>
<button id="btn3">Send [5,6,12] Three</button><br/>
<button id="btn4">Send Compose Object</button><br/>
<button id="btn5">Layer Alert</button><br/>
<a href="admin/to/login/page.html">管理员登录页面</a><br/>

<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script>
    $(function () {
        $('#btn1').on('click', function () {
            $.ajax({
                url: 'send/array/one.html',
                type: 'post',
                data: {
                    'array': [5, 6, 12]
                },
                dataType: 'text',
                success: function (response) {
                    alert(response);
                },
                error: function (response) {
                    alert(response);
                },
            });
        });
        $('#btn2').on('click', function () {
            $.ajax({
                url: 'send/array/two.html',
                type: 'post',
                data: {
                    'array[0]': 5,
                    'array[1]': 6,
                    'array[2]': 12
                },
                dataType: 'text',
                success: function (response) {
                    alert(response);
                },
                error: function (response) {
                    alert(response);
                },
            });
        });
        var array = [5,6,12];
        console.log(array);
        var json = JSON.stringify(array);
        console.log(json);
        $('#btn3').on('click', function () {
            $.ajax({
                url: 'send/array/three.html',
                type: 'post',
                contentType: 'application/json;charset=UTF-8',
                data: json,
                dataType: 'text',
                success: function (response) {
                    alert(response);
                },
                error: function (response) {
                    alert(response);
                },
            });
        });

        var data = {
            stuId: '20220709001',
            stuName: '小明',
            address: {
                province: '江苏',
                city: '苏州',
                street: '姑苏区'
            },
            subjectList: [
                {subName: 'Spring', subScore: 100,},
                {subName: 'SpringMVC', subScore: 100,},
                {subName: 'MyBatis', subScore: 100,}
            ],
            map: {
                k1: 'v1',
                k2: 'v2'
            }
        };
        console.log(data);
        var jsonStr = JSON.stringify(data);
        console.log(jsonStr);
        $('#btn4').on('click', function () {
            $.ajax({
                // url: 'send/compose/subject.html',
                url: 'send/compose/subject.json',
                type: 'post',
                contentType: 'application/json;charset=UTF-8',
                data: jsonStr,
                // dataType: 'html',
                dataType: 'json',
                success: function (response) {
                    console.log(response);
                },
                error: function (response) {
                    console.log(response);
                },
            });
        });

        $('#btn5').on('click', function () {
            layer.msg('Layer');
        });
    });
</script>
</body>
</html>
