# Sistema de Biblioteca - Modelo de Datos (POO)

Este repositorio contiene la capa del modelo de datos para un sistema de gestión de biblioteca de escritorio, desarrollado en Java aplicando los principios fundamentales de la Programación Orientada a Objetos (POO).

## Autor
* **Bryan Daniel Contreras Martinez** 
* Actividad 2.2 - Diseño e implementación del dominio de clases modelo.

## Estructura del Modelo

El dominio está compuesto por 10 clases estructuradas para garantizar alta cohesión y bajo acoplamiento:

* **Entidades Base (Abstractas):** `Persona`, `Material`.
* **Actores (Finales):** `Usuario`, `Bibliotecario`, `Autor`.
* **Recursos Físicos (Finales):** `Libro`, `Revista`.
* **Catálogos:** `Editorial`.
* **Transacciones:** `Prestamo`, `Multa`.

## Principios Técnicos Aplicados

1. **Herencia y Polimorfismo:** Uso de clases base `abstract` para evitar instanciación directa y encapsulación de atributos compartidos. Uso de la etiqueta `final` en clases hoja para asegurar la integridad de la jerarquía.
2. **Encapsulamiento Estricto:** Todos los atributos son `private`, con acceso exclusivo a través de métodos `getter` y `setter`.
3. **Constructores:** Cada entidad cuenta con un constructor vacío (por defecto) y un constructor sobrecargado con los datos mínimos indispensables para su integridad.
4. **Sobrescritura Estándar:** Implementación de `equals()` y `hashCode()` basados en el identificador primario (`id` entero), y adaptación de `toString()` concatenando estados de la superclase (`super.toString()`).
5. **Documentación:** Código fuente documentado con el estándar Javadoc (`@author`, `@param`, `@return`).

## Entorno de Desarrollo
* Lenguaje: Java
* IDE sugerido: Apache NetBeans / IntelliJ IDEA
* Empaquetado: `edu.utj.dsm.poo.biblioteca.modelo`
