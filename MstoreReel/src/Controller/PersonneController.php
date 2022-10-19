<?php

namespace App\Controller;


use App\Repository\PersonneRepository;
use App\Entity\Personne;
use App\Entity\PersonneRecherche;
use App\Form\PersonneRechercheType;
use App\Form\PersonneType;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

/**
 * @Route("/personne")
 */
class PersonneController extends AbstractController
{


    /**
     * @Route("recherche/{r}", name="personne_recherche", methods={"GET"})
     */

    public function search(Request $request, PersonneRepository $personneRepository): Response
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery("SELECT p FROM App\Entity\Personne p WHERE p.email LIKE :r");
        $query->setParameters('r', $request->request->get('r'));
        $personnes = $query->getResult();
        return $this->render('personne/index.html.twig', [
            'personnes' => $personnes,
        ]);
    }


    /**
     * @Route("/", name="personne_index", methods={"GET"})
     */
    public function index(): Response
    {
        $personnes = $this->getDoctrine()
            ->getRepository(Personne::class)
            ->findAll();

        return $this->render('personne/index.html.twig', [
            'personnes' => $personnes,
        ]);
    }

    /**
     * @Route("/new", name="personne_new", methods={"GET","POST"})
     */
    public function new(Request $request,UserPasswordEncoderInterface $encoder): Response
    {
        $personne = new Personne();
        $form = $this->createForm(PersonneType::class, $personne);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $personne->getPhoto();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $personne->setPhoto($fileName);

            $hash = $encoder->encodePassword($personne , $personne->getPassword());
            $personne->setPassword($hash);

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($personne);
            $entityManager->flush();

            return $this->redirectToRoute('personne_index');
        }

        return $this->render('personne/new.html.twig', [
            'personne' => $personne,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="personne_show", methods={"GET"})
     */
    public function show(Personne $personne): Response
    {
        return $this->render('personne/show.html.twig', [
            'personne' => $personne,
        ]);
    }



    /**
     * @Route("/{id}/edit", name="personne_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Personne $personne ,UserPasswordEncoderInterface $encoder): Response
    {
        $form = $this->createForm(PersonneType::class, $personne);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $personne->getPhoto();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $personne->setPhoto($fileName);
            $hash = $encoder->encodePassword($personne , $personne->getPassword());
            $personne->setPassword($hash);

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('personne_index');
        }

        return $this->render('personne/edit.html.twig', [
            'personne' => $personne,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="personne_delete", methods={"POST"})
     */
    public function delete(Request $request, Personne $personne): Response
    {
        if ($this->isCsrfTokenValid('delete'.$personne->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($personne);
            $entityManager->flush();
        }

        return $this->redirectToRoute('personne_index');
    }












    /**
     * @Route("/personne/list/pdf", name="personne_index_listp", methods={"GET"})
     */
    public function listp(PersonneRepository $personneRepository): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('isHtml5ParserEnabled', true);
        $pdfOptions->set('isRemoteEnabled', true);
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $personnes = $personneRepository->findAll();
        $html = $this->renderView('personne/listp.html.twig', [
            'personnes' => $personnes,

        ]);
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
        return new Response('', 200, [
            'Content-Type' => 'application/pdf',
        ]);
    }
}






