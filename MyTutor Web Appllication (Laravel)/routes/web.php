<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\SubjectListController;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('landing', [AuthController::class, 'index'])->name('landing');
Route::get('registration', [AuthController::class, 'register'])->name('register');
Route::get('login', [AuthController::class, 'login'])->name('login');
Route::post('post-registration', [AuthController::class, 'postRegistration'])->name('register.post');
Route::post('post-login', [AuthController::class, 'postLogin'])->name('login.post');
Route::get('logout', [AuthController::class, 'logout'])->name('logout');

Route::post('/savesubject', [SubjectListController::class,'saveSubject'])->name('savesubject');
Route::get('mainpage', [SubjectListController::class, 'mainpage'])->name('mainpage');
Route::post('/markDeleteRoute/{id}', [SubjectListController::class, 'markDelete'])->name('markDelete');
Route::post('/markUpdateRoute/{id}', [SubjectListController::class, 'markUpdate'])->name('markUpdate');