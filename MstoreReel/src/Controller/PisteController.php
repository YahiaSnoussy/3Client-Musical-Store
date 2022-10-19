<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Entity\Piste;
use App\Form\PisteType;
use App\Repository\PisteRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/piste")
 */
class PisteController extends AbstractController
{
    /**
     * @Route("/", name="piste_index", methods={"GET"})
     */
    public function index(PisteRepository $pisteRepository, Request $request = null): Response
    {
        $search = $request->get('search');
        if($request != null && $search!=''&&$search!='ç_&éè<&àçé546é&&')
            return $this->render('piste/index.html.twig', [
            'pistes' => array_filter(array_map(function ($piste)use (&$search){
                if(str_contains($piste->getName(),$search)) return $piste;
                //else echo $search;
            },$pisteRepository->findAll())),
        ]);
        return $this->render('piste/index.html.twig', [
            'pistes' => $pisteRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="piste_new", methods={"GET","POST"})
     */

    public function new(Request $request): Response
    {
        $piste = new Piste();

        $form = $this->createForm(PisteType::class, $piste);

        $form->handleRequest($request);
        $entityManager=($this)->getDoctrine()->getManager();
        $pistes = $entityManager->getRepository(Piste::class);

        if ($form->isSubmitted() && $form->isValid()) {
            #set ID
            $piste->setId($piste->get_string_between($piste->getSlink(),"v=","&"));
            if(empty(array_filter($pistes->findBy(['id' => $piste->getId()] )))) {
                echo "<script>setTimeout(function() {alert('en cours dajout')},3000);</script>";
                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($piste);
                $entityManager->flush();
                $piste->email("Bonjour, \n\rVotre piste".$piste->getName()." a été ajoutée avec succès",$piste->getEmail());
            }else{
                echo alert('cette piste existe deja!');

            }

            return $this->redirectToRoute('piste_index');
        }

        return $this->render('piste/new.html.twig', [
            'piste' => $piste,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="piste_show", methods={"GET"})
     */
    public function show(Piste $piste): Response
    {

        return $this->render('piste/show.html.twig', [
            'piste' => $piste,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="piste_edit", methods={"GET","POST"})
     */

    public function edit(Request $request, Piste $piste): Response
    {
        $form = $this->createForm(PisteType::class, $piste);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('piste_index');
        }
        return $this->render('piste/edit.html.twig', [
            'piste' => $piste,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/{id}", name="piste_delete", methods={"POST"})
     */
    public function delete(Request $request, Piste $piste): Response
    {
        if ($this->isCsrfTokenValid('delete'.$piste->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($piste);
            $entityManager->flush();
        }

        return $this->redirectToRoute('piste_index');
    }
    /**
     * @Route("/{search}/search", name="piste_search", methods={"GET","Poste"})
     */
    public function search(Request $request, string $search): Response
    {
        return $response = $this->forward('App\Controller\PisteController::index', [
            'search' => $search,
        ]);
    }
    /**
     * @Route("/{id}/add", name="piste_add", methods={"GET","Poste"})
     */
    public function add(Request $request, Piste $piste): Response
    {
        //echo "<script type='text/javascript'>alert('test');</script>";
        $entityManager=($this)->getDoctrine()->getManager();
        $paniers = $entityManager->getRepository(Panier::class);

        if(empty(array_filter($paniers->findBy(['pisteid' => $piste->getId()] )))){
            echo "<script>alert('en cours dajout')</script>";
            $response = $this->forward('App\Controller\PanierController::new', [
                'id'  => $piste->getId(),
                'nom'  => $piste->getName(),
                'prix'  => $piste->getPrix(),
            ]);
            return $response;
        }

        else {
            echo "<script>alert('produit déjà acquis')</script>";
            return $response = $this->forward('App\Controller\PisteController::index');
        }

        // ... further modify the response or return it directly


    }
}
