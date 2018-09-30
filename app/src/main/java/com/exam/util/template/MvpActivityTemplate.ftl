package ${classPath};

import ${basePackage}.R
import com.exam.basecomponent.MvpActivity
import ${classPath}.presenter.${name}Presenter
import ${classPath}.contract.${name}Contract

class LoginActivity : MvpActivity<${name}Contract.Presenter>(), ${name}Contract.View {

    override fun getLayout(): Int {
        return R.layout.activity_${name?lower_case}
    }

    override fun init() {

    }

    override fun getPresenter(): ${name}Contract.Presenter {
        return ${name}Presenter()
    }
}