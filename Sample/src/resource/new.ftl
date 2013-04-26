<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
Create a new simple Bicycle
<form action="/bicycle/create" method="post">
    <label for="bicycle.wheel.radius">Wheel radius</label>
    <input id="bicycle.wheel.radius" name="bicycle.wheel.radius" type="text"/>
    <br>
    <label for="bicycle.wheelNum">Wheel number</label>
    <input id="bicycle.wheelNum" name="bicycle.wheelNum" type="text"/>
    <br>
    <input type="submit" value="submit"/>
</form>
</body>
</html>