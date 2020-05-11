<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-csv/0.71/jquery.csv-0.71.min.js"></script>
        <script type="text/javascript">
            function readFile () {
                console.log('начало')
                $.ajax({
                    type: 'GET',
                    contentType: 'application/json',
                    url: '/readCSV',
                    dataType: "json",
                    data: "",
                    success: function (dataAnswer) {
                        console.log(dataAnswer.length);
                        $.makeTable = function (mydata) {
                            var table = $('<table border=1>');
                            var tblHeader = "<tr>";
                            for (var k in mydata[0]) tblHeader += "<th>" + k + "</th>";
                            tblHeader += "</tr>";
                            $(tblHeader).appendTo(table);
                            $.each(mydata, function (index, value) {
                                var TableRow = "<tr>";
                                $.each(value, function (key, val) {
                                    TableRow += "<td>" + val + "</td>";
                                });
                                TableRow += "</tr>";
                                $(table).append(TableRow);
                            });
                            return ($(table));
                        };
                        var parag = "<h3>Список песен из файла прочитанного из папки resources</h3>";
                        $(parag).appendTo("#headText")

                        var table = $.makeTable(dataAnswer);
                        $(table).appendTo("#TableCont")
                    },
                });
            }
    </script>
</head>
<body>
<div id="dvImportSegments">
    <div>
        <input type="button" onclick="readFile()" value="Прочитать фаил">
    </div>
    <div id="headText"></div>
    <div id="TableCont"></div>
</div>
</body>
</html>