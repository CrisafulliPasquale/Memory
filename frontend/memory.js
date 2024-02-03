$(document).ready(function() {
	var board = [];
	var cards = ['A', 'A', 'B', 'B', 'C', 'C', 'D', 'D'];
	var openedCards = [];

	function shuffle(array) {
		for (let i = array.length - 1; i > 0; i--) {
			let j = Math.floor(Math.random() * (i + 1));
			[array[i], array[j]] = [array[j], array[i]];
		}
	}

	function drawBoard(board) {
		$('#board').empty();
		for (let i = 0; i < board.length; i++) {
			let card = $('<div>').addClass('card').text(cards[i]);
			if (board[i] == 'hidden') {
				card.addClass('hidden');
			} else if (board[i] == 'visible') {
				card.addClass('visible');
			} else if (board[i] == 'matched') {
				card.addClass('matched');
			}
			card.click(function() {
				if (openedCards.length < 2) {
					openedCards.push(i);
					$.post('memory.php', { action: 'flip', index: i }, function(data) {
						board = JSON.parse(data);
						drawBoard(board);
					});
				} else {
					$.post('memory.php', { action: 'check', index1: openedCards[0], index2: i }, function(data) {
						board = JSON.parse(data);
						openedCards = [];
						drawBoard(board);
					});
				}
			});
			$('#board').append(card);
		}
	}

	shuffle(cards);
	drawBoard(board);

	$('#reset').click(function() {
		$.post('../backend/memory.php', { action: 'reset' }, function(data) {
			board = JSON.parse(data);
			drawBoard(board);
		});
	});
});