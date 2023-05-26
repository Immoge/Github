<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\tutor;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Session;

class AuthController extends Controller
{
    public function index()
    {
        return view('landing');
    } //
   
    public function postRegistration(Request $request)
    {
        $request->validate([
            'name' => 'required',
            'email' => 'required|email|unique:tutors',
            'phone' => 'required',
            'address' => 'required',
            'state' => 'required',
            'password' => 'required|min:6',
        ]);
        $data = $request->all();
        $check = $this->create($data);
        $check->save();
        return redirect("login")->with('save', 'Success')->withErrors('error', 'Failed');;
    }
   
    public function create(array $data)
    {
        return Tutor::create([
            'name' => $data['name'],
            'email' => $data['email'],
            'phone' => $data['phone'],
            'address' => $data['address'],
            'state' => $data['state'],
            'password' => Hash::make($data['password'])
        ]);
    }

    public function register()
    {
        return view('tutorregister');
    }
    
    public function login()
    {
        return view('tutorlogin');
    }
    
    public function postLogin(Request $request)
    {
        $request->validate([
            'email' => 'required',
            'password' => 'required',
        ]);
        $credentials = $request->only('email', 'password');
        if (Auth::attempt($credentials)) {
            return redirect()->intended('mainpage')->with('save', 'Success')->withErrors('error', 'Failed');
        }

        return redirect("login")->withSuccess('You have invalid credentials');
    }
   

    public function logout()
    {
        Session::flush();
        Auth::logout();
        return Redirect('login');
    }
}
