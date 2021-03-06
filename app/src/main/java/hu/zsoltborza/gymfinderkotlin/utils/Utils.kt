package hu.zsoltborza.gymfinderkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.Navigator
import hu.zsoltborza.gymfinderkotlin.R
import hu.zsoltborza.gymfinderkotlin.data.local.model.GymData
import hu.zsoltborza.gymfinderkotlin.data.local.model.GymListItem
import java.util.*

fun View.onClick(clickListener: (View) -> Unit) {
    setOnClickListener(clickListener)
}

inline fun EditText.onTextChanged(crossinline textChangeListener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            textChangeListener(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}

fun Unit.safe() = Unit

fun Any.safe() = Unit

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun <T : View> T.showIf(condition: (T) -> Boolean): T {
    if (condition(this)) {
        show()
    } else {
        hide()
    }

    return this
}

fun <T : View> T.hideIf(condition: (T) -> Boolean): T {
    if (condition(this)) {
        hide()
    } else {
        show()
    }

    return this
}

val Fragment.requireArguments
    get() = this.arguments ?: throw IllegalStateException("Arguments should exist!")

fun Backstack.replaceHistory(vararg keys: Any) {
    this.setHistory(keys.toList(), StateChange.REPLACE)
}

val Activity.backstack: Backstack get() = Navigator.getBackstack(this)

val Fragment.backstack: Backstack get() = com.zhuinden.simplestack.navigator.Navigator.getBackstack(requireActivity())


/**
 * Reading gyms from file, returning as a list.
 * @param context
 * @return List of questions
 */
fun getDataFromFile(context: Context): List<GymListItem> {

    val res = context.resources

    val builder = StringBuilder()
    val `is` = res.openRawResource(R.raw.marker)
    val scanner = Scanner(`is`)

    while (scanner.hasNextLine()) {
        builder.append(scanner.nextLine())
    }

    val file = builder.toString()

    val gson = Gson()

    val gymList = gson.fromJson(file, GymData::class.java)

    return gymList.gymList!!
}