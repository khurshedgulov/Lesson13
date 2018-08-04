package company.my.lesson13;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SampleFragment extends Fragment {

    // Функция onCreateView создаёт главный View фрагмента и возвращает его Activity
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Через экземпляр класса Inflater привязываем файл макета к фрагменту
        // LayoutInflater.inflate аналогичен вызову функции setContentView в Activity
        View v = inflater.inflate(R.layout.fragment_layout, container, false);

        // Получаем ссылку на TextView в нашем фрагменте
        // Обратите внимание что id TextView получаем через поиск внутри
        // View который привязали через функцию inflate
        TextView textView = v.findViewById(R.id.text);

        // try catch используется для перехвата исключений (ошибок), чтобы система не закрывала приложение
        // тут используется перехват исключений, потому что, если параметры не переданы с Activity
        // через вызов функции setArguments, выбрасывается исключение, а в коде параметры при
        // первоначальном включении фрагмента в Activity не были добавлены, а только создавался пустой
        // экземпляр фрагмента
        try {
            textView.setText(getArguments().getString("name"));
        } catch (Exception e)
        {
            // Вывести в консоль LogCat сообщение об исключении
            // и порядок вызова функций из стэка вызовов функци до строки вызвавшей исключение
            e.printStackTrace();
        }
        return v;
    }
}
