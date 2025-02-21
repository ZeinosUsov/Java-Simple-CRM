/**
 * Скрываем сообщения на странице авторизации
 */
function hideMessages() {
    if (window.location.href.includes('logout=true')) {
        document.getElementById('logoutAlert').style.display = 'none';
    } else {
        document.getElementById('errorAlert').style.display = 'none';
    }
}


/**
 * Скрипт для возврата на последнюю страницу
 */
function onBack() {
    window.history.back();
}

/**
 * Маска для ввода номера телефона
 */
$(function() {
    const element = document.getElementById('phone');
    const maskOptions = {
        mask: '+7(000)000-00-00',
        lazy: false
    };
    const mask = new IMask(element, maskOptions);
});

/**
 * Функция для скрытия валидаций при нажатие на поле ввода
 *
 * function hideValidationRegForm() {
 *     const form = document.querySelector('form');
 *     const invalidFeedbacks = form.querySelectorAll('.text-danger');
 *     invalidFeedbacks.forEach(function(feedback) {
 *         feedback.style.display = 'none';
 *     });
 * }
 */
