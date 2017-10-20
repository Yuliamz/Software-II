'use strict';

module.controller('EstudianteCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = [];
        $scope.listaFiltrada = [];
        $scope.datosFormulario = {};
        $scope.opcionBuscar = "Nombre";
        $scope.panelCrear = true;
        $scope.query="";
        $scope.textApellido=true;
        $scope.textNombre=false;
        
        $scope.buscarPorNombre = function (){
            $scope.query="./webresources/Estudiante/buscar?nombre="+$scope.nombres;
            $http.get($scope.query, {})
                    .success(function (data, status, headers, config) {
                        $scope.listaFiltrada = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.buscarPorApellido = function (){
            $scope.query="./webresources/Estudiante/buscar?apellido="+$scope.apellidos;
            $http.get($scope.query, {})
                    .success(function (data, status, headers, config) {
                        $scope.listaFiltrada = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.buscarPorNombreYApellido = function (){
            $scope.query="./webresources/Estudiante/buscar?nombre="+$scope.nombres+"&apellido="+$scope.apellidos;
            $http.get($scope.query, {})
                    .success(function (data, status, headers, config) {
                        $scope.listaFiltrada = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        
        
        $scope.listar = function () {
            $http.get('./webresources/Estudiante', {})
                    .success(function (data, status, headers, config) {
                        $scope.lista = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.listarCarrera = function () {
            $http.get('./webresources/Carrera', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaCarrera = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de carrera, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarCarrera();
        $scope.listarLugarNacimiento = function () {
            $http.get('./webresources/Municipio', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaLugarNacimiento = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de lugarNacimiento, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarLugarNacimiento();


        $scope.listar();
        //guardar
        $scope.nuevo = function () {
            console.log("Abre el panel para crear un  nuevo estudiante");
            $scope.panelCrear = true;
            $scope.panelListar = false;
            $scope.panelBuscar = false;
            
            $scope.datosFormulario = {};
        };
        
        $scope.buscar = function () {
            console.log("Abre el panel para buscar los estudiantes");
            $scope.panelCrear = false;
            $scope.panelListar = false;
            $scope.panelBuscar = true;
            $scope.datosFormulario = {};
        };
        
        $scope.mostrar = function () {
            console.log("Abre el panel para listar los estudiantes");
            $scope.panelCrear = false;
            $scope.panelListar = true;
            $scope.panelBuscar = false;
            $scope.datosFormulario = {};
        };
        
        $scope.cambio = function(){
            $scope.textApellido=$scope.opcionBuscar!=="Nombre"?false:true;
            $scope.textNombre=$scope.opcionBuscar=="Apellido"?true:false;
            console.log("Se cambio a: "+$scope.opcionBuscar);
        };
        $scope.busquedaFiltrada = function(){
            console.log("Se busca por: "+$scope.opcionBuscar);
            switch ($scope.opcionBuscar){
                case "Nombre": $scope.buscarPorNombre(); break;
                case "Apellido": $scope.buscarPorApellido(); break;
                case "Nombre y Apellido": $scope.buscarPorNombreYApellido(); break;
                    default :$scope.buscarPorNombre(); break;
            }
            
        };

        $scope.guardar = function () {
            $scope.errores = {};
            var error = false;

            if (error)
                return;
            $http.post('./webresources/Estudiante', JSON.stringify($scope.datosFormulario), {}
            ).success(function (data, status, headers, config) {
                alert("Los datos han sido guardados con Exito");
            $scope.panelCrear = false;
            $scope.panelListar = true;
            $scope.panelBuscar = false;
                $scope.listar();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };


        //editar
        $scope.editar = function (data) {
            $scope.panelCrear = true;
            $scope.panelListar = false;
            $scope.panelBuscar = false;
            $scope.datosFormulario = data;
        };
        //eliminar
        $scope.eliminar = function (data) {
            if (confirm('\xbfDesea elminar este registro?')) {
                $http.delete('./webresources/Estudiante/' + data.id, {})
                        .success(function (data, status, headers, config) {
                            $scope.listar();
                        }).error(function (data, status, headers, config) {
                    alert('Error al eliminar la informaci\xf3n de Estudiante, por favor intente m\xe1s tarde');
                });
            }
        };
    }]);
