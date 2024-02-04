<?php
session_start();

if (!isset($_SESSION['attempts'])) {
    $_SESSION['attempts'] = 3;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $action = $_POST['action'];

    if ($action === 'reset') {
        $_SESSION['attempts'] = 3;
    } elseif ($action === 'decrease') {
        $_SESSION['attempts']--;
    }

    echo $_SESSION['attempts'];
}