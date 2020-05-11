<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-csv/0.71/jquery.csv-0.71.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            document.getElementById('txtFileUpload').addEventListener('change', upload, false);

             function upload(evt) {
                    var data;
                    var file = evt.target.files[0];
                    var reader = new FileReader();
                    reader.readAsText(file);

                    reader.onload = function(event) {
                        let csvData = event.target.result;
                        data = $.csv.toArrays(csvData);
                        console.log(data);
                        var reqRow = [];
                        for (let i = 1; i < data.length; i++)
                        {
                            var rowObj = {};
                            rowObj.songName = data[i][0];
                            reqRow.push(rowObj);
                        }

                        console.log(reqRow);
                        $.ajax({
                            type:'POST',
                            contentType: 'application/json',
                            url:'/songsList',
                            dataType:"json",
                            data : JSON.stringify({rows: reqRow}),
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

                                var parag = "<h3>Список песен из файла добавлен</h3>";
                                $(parag).appendTo("#headText")

                                var table = $.makeTable(dataAnswer);
                                $(table).appendTo("#TableCont")

                            },
                        });
                    };
                    reader.onerror = function() {
                        alert('Файлик не читается ' + file.fileName);
                    };

                }
        });
    </script>
</head>
<body>
<div id="dvImportSegments">
    <div>
        <legend>Выберите фаил</legend>
        <input type="file" name="File Upload" id="txtFileUpload" accept=".csv" />
    </div>
    <div id="headText"></div>
    <div id="TableCont"></div>
</div>
</body>
</html>