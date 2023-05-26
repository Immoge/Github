<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Subject;
use Illuminate\Support\Facades\Auth;

class SubjectListController extends Controller
{
    public function savesubject(Request $request)
    {
        echo json_encode($request->all());
        $newSubjectList = new Subject();
        $newSubjectList->subject_title = $request->subtitle;
        $newSubjectList->subject_description = $request->subdesc;
        $newSubjectList->subject_price = $request->subprice;
        $newSubjectList->subject_hour = $request->subhour;
        $newSubjectList->save();
        return redirect('mainpage')->with('save', 'Success')->withErrors('error', 'Failed');
    }

    public function mainpage()
    {
        if (Auth::check()) {
            return view('mainpage', ['listSubjects' => Subject::all()]);
        }
        return redirect("login")->withSuccess('You do not have access');
    }
    public function markDelete($id)
    {
        $listSubject = Subject::find($id);
        $listSubject->delete();
        return redirect('mainpage');
    }

    public function markUpdate($id, Request $request)
    {
        $listSubject = Subject::find($id);
        $listSubject->subject_title = $request->subtitle;
        $listSubject->subject_description = $request->subdesc;
        $listSubject->subject_price = $request->subprice;
        $listSubject->subject_hour = $request->subhour;
        $listSubject->update();
        return redirect('mainpage');
    }
}

