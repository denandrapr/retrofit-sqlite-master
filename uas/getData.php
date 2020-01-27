<?php
    $db=mysqli_connect('localhost', 'root', '', 'api_favorit');
    $result=array();
    $query="SELECT * FROM mahasiswa";
    $rowData=mysqli_query($db, $query);
    while($row=mysqli_fetch_assoc($rowData)){
        $result["data"][]=$row;
    }

    echo json_encode($result);
?>