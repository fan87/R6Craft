package org.example.r6craft.config

import org.example.r6craft.R6Craft
import java.lang.reflect.Field
import java.lang.reflect.Modifier

object ConfigManager {

    val defaultValues = HashMap<String, Any>()
    val fields = HashMap<String, Field>()
    val inverseFields = HashMap<Field, String>()

    init {
        visit(Config::class.java)
        loadConfig()
    }

    fun loadConfig() {
        for (defaultValue in defaultValues) {
            if (!R6Craft.plugin.config.contains(defaultValue.key)) {
                R6Craft.plugin.config[defaultValue.key] = defaultValue.value
            } else {
                fields[defaultValue.key]!!.set(null, R6Craft.plugin.config[defaultValue.key])
            }
        }
        R6Craft.plugin.saveConfig()
    }
    
    fun saveConfig() {
        for (field in fields) {
            R6Craft.plugin.config[field.key] = field.value.get(null)
        }
        R6Craft.plugin.saveConfig()
    }

    private fun visit(clazz: Class<*>, prefix: String = "") {
        for (field in clazz.declaredFields.filter { Modifier.isStatic(it.modifiers) && Modifier.isPublic(it.modifiers) }) {
            val name = "$prefix${field.name}"
            defaultValues[name] = field.get(null)
            fields[name] = field
            inverseFields[field] = name
        }
        for (innerClazz in clazz.classes.filter { Modifier.isStatic(it.modifiers) && Modifier.isPublic(it.modifiers) }) {
            visit(innerClazz, "$prefix${innerClazz.simpleName}.")
        }
    }

}