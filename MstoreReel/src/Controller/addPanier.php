<?php


namespace App\Controller;


use App\Entity\Piste;

class addPanier
{

    public function add(Piste $piste): Response
    {
        try {
            $number = random_int(0, 100);
        } catch (\Exception $e) {
        }

        return new Response(
            '<html><body>Lucky number: ' . $number . '</body></html>'
        );
    }

}