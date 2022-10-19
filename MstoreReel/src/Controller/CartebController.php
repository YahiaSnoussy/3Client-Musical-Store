<?php

namespace App\Controller;

use App\Entity\Carteb;
use App\Form\CartebType;
use App\Repository\CartebRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/carteb")
 */
class CartebController extends AbstractController
{
    /**
     * @Route("/", name="carteb_index", methods={"GET"})
     */
    public function index(CartebRepository $cartebRepository): Response
    {
        return $this->render('carteb/index.html.twig', [
            'cartebs' => $cartebRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="carteb_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $carteb = new Carteb();
        $form = $this->createForm(CartebType::class, $carteb);
        $form->handleRequest($request);
        $carteb->setId(str_pad(rand(0,999999),6, "0", STR_PAD_LEFT));
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($carteb);
            $entityManager->flush();
            $carteb->email("Bonjour monsieur/madame ".$carteb->getNom().", \r\n votre paiement est en train d'etre traité.", $carteb->getEmail());
            return $this->redirectToRoute('piste_index');
        }

        return $this->render('carteb/new.html.twig', [
            'carteb' => $carteb,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="carteb_show", methods={"GET"})
     */
    public function show(Carteb $carteb): Response
    {
        return $this->render('carteb/show.html.twig', [
            'carteb' => $carteb,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="carteb_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Carteb $carteb): Response
    {
        $form = $this->createForm(CartebType::class, $carteb);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('carteb_index');
        }

        return $this->render('carteb/edit.html.twig', [
            'carteb' => $carteb,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="carteb_delete", methods={"POST"})
     */
    public function delete(Request $request, Carteb $carteb): Response
    {
        if ($this->isCsrfTokenValid('delete'.$carteb->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($carteb);
            $entityManager->flush();
        }

        return $this->redirectToRoute('carteb_index');
    }
}
