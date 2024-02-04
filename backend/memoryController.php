<?php
session_start();

if (!isset($_SESSION['attempts'])) {
    $_SESSION['attempts'] = 5;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $action = $_POST['action'];

    if ($action === 'reset') {
        $_SESSION['attempts'] = 5;
    } elseif ($action === 'decrease') {
        $_SESSION['attempts']--;
    }

    echo $_SESSION['attempts'];
}