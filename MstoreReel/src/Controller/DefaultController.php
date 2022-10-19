<?php


namespace App\Controller;



use App\Entity\Personne;
use App\Form\PersonneType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class DefaultController extends AbstractController
{
    /**
     * @Route("/" , name="home")
     */
 function home ()
 {
     return $this->render('base.html.twig');
 }


    /**
     * @Route("/profil", name="personne_profil")
     */
    public function profil()
    {


        return $this->render('x.html.twig', [

        ]);
    }
    /**
     * @Route("/profil/{id}/edit", name="app_edit" , methods={"GET","POST"})
     */
    public function profiledit(Request $request, Personne $personne,UserPasswordEncoderInterface $encoder): Response
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

            return $this->redirectToRoute('personne_profil');
        }

        return $this->render('personne/editprofil.html.twig', [
            'personne' => $personne,
            'form' => $form->createView(),
        ]);
    }


}