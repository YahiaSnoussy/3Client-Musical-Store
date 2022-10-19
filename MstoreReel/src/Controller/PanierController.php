<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Entity\Piste;
use App\Form\PanierType;
use App\Repository\PanierRepository;
use App\Repository\PisteRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/panier")
 */


class PanierController extends AbstractController
{
    /**
     * @Route("/", name="panier_index", methods={"GET"})
     * @param PanierRepository $panierRepository
     * @return Response
     */
    public function index(PanierRepository $panierRepository): Response
    {
        return $this->render('panier/index.html.twig', [
            'paniers' => $panierRepository->findAll(),
             //'pistes' => array_map(array($panierRepository,'findPiste'),
                // array_map(function($o){return "";},$panierRepository->findAll()))
        ]);
    }

    /**
     * @Route("/new", name="panier_new", methods={"GET","POST"})
     * @param Request $request
     * @return Response
     */
    public function new(Request $request): Response
    {
        $panier = new Panier();
        $panier->setUserid(1);
        $panier->setPisteid($request->get('id'));
        $panier->setNom($request->get('nom'));
        $panier->setPrix($request->get('prix'));
        $panier->setId(str_pad(rand(0,999999),6, "0", STR_PAD_LEFT));

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($panier);
        $entityManager->flush();

            return $this->redirectToRoute('piste_index');

    }

    /**
     * @Route("/{id}", name="panier_show", methods={"GET"})
     */
    public function show(Panier $panier): Response
    {
        return $this->render('panier/show.html.twig', [
            'panier' => $panier,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="panier_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Panier $panier): Response
    {
        $form = $this->createForm(PanierType::class, $panier);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('panier_index');
        }

        return $this->render('panier/edit.html.twig', [
            'panier' => $panier,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="panier_delete", methods={"POST"})
     */
    public function delete(Request $request, Panier $panier): Response
    {
        if ($this->isCsrfTokenValid('delete'.$panier->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($panier);
            $entityManager->flush();
        }

        return $this->redirectToRoute('panier_index');
    }
}
