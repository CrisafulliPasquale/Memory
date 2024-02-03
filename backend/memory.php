<?php
if (isset($_POST['action'])) {
	$action = $_POST['action'];
	if ($action == 'reset') {
		$board = array_fill(0, 8, 'hidden');
		echo json_encode($board);
	} elseif ($action == 'flip') {
		$index = $_POST['index'];
		$board[$index] = 'visible';
		echo json_encode($board);
	} elseif ($action == 'check') {
		$index1 = $_POST['index1'];
		$index2 = $_POST['index2'];
		if ($board[$index1] == 'visible' && $board[$index2] == 'visible') {
			$board[$index1] = 'matched';
			$board[$index2] = 'matched';
		}
		echo json_encode($board);
	}
} else {
	$board = array_fill(0, 8, 'hidden');
	echo json_encode($board);
}
