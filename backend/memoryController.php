<?php
session_start();

if (!isset($_SESSION['board'])) {
    $_SESSION['board'] = array_fill(0, 20, 'hidden');
    $_SESSION['attempts'] = 3;
}

if (isset($_POST['action'])) {
    $action = $_POST['action'];
    if ($action == 'reset') {
        $_SESSION['board'] = array_fill(0, 20, 'hidden');
        $_SESSION['attempts'] = 3;
        echo json_encode($_SESSION['board']);
    } elseif ($action == 'flip') {
        $index = $_POST['index'];
        $_SESSION['board'][$index] = 'visible';
        echo json_encode($_SESSION['board']);
    } elseif ($action == 'check') {
        $index1 = $_POST['index1'];
        $index2 = $_POST['index2'];
        if ($_SESSION['board'][$index1] == 'visible' && $_SESSION['board'][$index2] == 'visible') {
            $_SESSION['board'][$index1] = 'matched';
            $_SESSION['board'][$index2] = 'matched';
        } else {
            $_SESSION['attempts']--;
            if ($_SESSION['attempts'] <= 0) {
                $_SESSION['board'] = array_fill(0, 20, 'hidden');
                $_SESSION['attempts'] = 3;
            }
        }
        echo json_encode($_SESSION['board']);
    }
} else {
    echo json_encode($_SESSION['board']);
}