package company.my.lesson13;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Объявление переменной для получения доступа к текстовому полю в макете
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getSupportFragmentManager() - возвращает ссылку на менеджер фрагментов
        // beginTransaction() - заявляет о начале транзакции (включения фрагмент в Activity)
        // setTransition() - устанавливает значение для включения фрагмента с анимацией перехода
        // add() - добавляет фрагмент в транзакцию
        // commit() - начинает транзакцию по включению фрагмента в Activity
        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.fragment_container1, new SampleFragment()).commit();

        // Строку 30 можно написать и так, от переноса строк читаемость и ввод изменений облегчились.
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.fragment_container2, new SampleFragment())
                .commit();

        // Получение ссылки на текстовое поле в файле макета
        editText = findViewById(R.id.name_field);
        // Функция setOnEditorActionListener() добавляет обработчик к нажатию кнопок (поиск, переход к следующему полю...)
        // объявленных в файле макета в атрибуте imeOptions, здесь значение imeOptions="actionSearch",
        // что заменяет кнопку Enter на значок поиска и при нажатии на кнопку Поиск вызывается код ниже
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // При нажатии на кнопку поиск функции onEditorAction в параметре actionId
                // передаётся значение определяющее какая кнопка была нажата и в switch case
                // идёт обработка
                switch (actionId)
                {
                    // Если наша кнопка это кнопка Поиск
                    case EditorInfo.IME_ACTION_SEARCH:
                        // Вызвать функцию ReplaceFragments()
                        ReplaceFragemnts();
                        break;
                }
                return false;
            }
        });
        // Ссылка на кнопку Send Info
        Button btn = findViewById(R.id.send);
        // Подключить обработчик событий для кнопки Send Info
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызвать функцию ReplaceFragments()
               ReplaceFragemnts();
            }
        });

    }

    // Пользовательская функция - функция которую программист сам от себя написал для облегчения своей работы
    // Пользовательские функции удобно использовать, если надо выполнить один и тот же код несколько раз
    // Такие функции являются часть написания DRY (Don't Repeat Yourself ) кода, что облегчает дальнейшее изменение
    // или дополнение подобных функция
    // ReplaceFragemnts - служит для замены существующих фрагментов на новые с переданным параметром
    void ReplaceFragemnts() {
        // Экземпляр класса фрагмента
        SampleFragment sampleFragment = new SampleFragment();
        // Экземпляр класса для передачи параметров
        Bundle args = new Bundle();
        // Добавление строки в список параметров нашего фрагмента
        args.putString("name", editText.getText().toString());
        // Привязать параметры к экземпляру фрагмента
        sampleFragment.setArguments(args);

        // getSupportFragmentManager() - возвращает ссылку на менеджер фрагментов
        // beginTransaction() - заявляет о начале транзакции (включения фрагмент в Activity)
        // setTransition() - устанавливает значение для включения фрагмента с анимацией перехода
        // add() - добавляет фрагмент в транзакцию
        // commit() - начинает транзакцию по включению фрагмента в Activity
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container1, sampleFragment)
                .addToBackStack(null)
                .commit();


        //  В строках 103-118 делается то же, что и в строках 81 - 100
        // только с другим экземпляром класса фрагмента
        SampleFragment secondFragment = new SampleFragment();
        Bundle args2 = new Bundle();
        // toUpperCase() - функция которую можно вызвать для строк
        // и служит для смены регистра символов на ПРОПИСНЫЕ
        args2.putString("name", editText.getText().toString().toUpperCase());
        secondFragment.setArguments(args2);

        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container2, secondFragment)
                .addToBackStack(null)
                .commit();
    }
}
