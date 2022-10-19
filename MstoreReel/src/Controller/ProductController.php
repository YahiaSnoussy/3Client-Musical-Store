<?php

namespace App\Controller;

use App\Entity\Comments;
use App\Entity\Product;
use App\Form\CommentsType;
use App\Form\ProductType;
use App\Form\SearchProductType;
use App\Repository\CommentsRepository;
use App\Repository\ProductRepository;
use DateTime;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/product")
 */
class ProductController extends AbstractController
{
    /**
     * @Route("/", name="product_index", methods={"GET","POST"})
     * @param Request $request
     * @param PaginatorInterface $paginator
     * @param ProductRepository $productrepo
     * @return Response
     */
    public function index(Request $request,PaginatorInterface $paginator, ProductRepository $productrepo): Response
    {
        $donnees = $this->getDoctrine()
            ->getRepository(Product::class)
            ->findAll();

        $formsearch = $this->createForm(SearchProductType::class);
        $search = $formsearch->handleRequest($request);
        if ($formsearch->isSubmitted() && $formsearch->isValid()){
            $products2 = $productrepo->search($search->get('mots')->getData());
            $products = $paginator->paginate($products2,$request->query->getInt('page',1),5);
        }else{
            $products = $paginator->paginate($donnees,$request->query->getInt('page',1),5);
        }

        return $this->render('product/index.html.twig', [
            'products' => $products,
            'form'=>$formsearch->createView()
        ]);
    }

    /**
     * @Route("/new", name="product_new", methods={"GET","POST"})
     * @param Request $request
     * @return Response
     */
    public function new(Request $request): Response
    {
        $product = new Product();
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($product);
            $entityManager->flush();

            return $this->redirectToRoute('product_index');
        }

        return $this->render('product/new.html.twig', [
            'product' => $product,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="product_show", methods={"GET"})
     * @param Product $product
     * @param Request $request
     * @return Response
     */
    public function show(Product $product,Request $request)
    {
        //comments part
        $comment = new Comments();
        //commentform
        $commentform = $this->createForm(CommentsType::class,$comment);
        $commentform->handleRequest($request);
        //comment stuff
        if ($commentform->isSubmitted() && $commentform->isValid()){
            $comment->setCreatedAt(new DateTime());
            $comment->setProduct($product);
            $em = $this->getDoctrine()->getManager();
            $em->persist($comment);
            $em->flush();
            $this->addFlash('message','Your comment has been added');
            return $this->redirectToRoute('product_show',['product'=>$product ]);

        }

        return $this->render('product/show.html.twig', [
            'product' => $product,
            'commentform'=>$commentform->createView()
        ]);
    }

    /**
     * @Route("/{id}/edit", name="product_edit", methods={"GET","POST"})
     * @param Request $request
     * @param Product $product
     * @return Response
     */
    public function edit(Request $request, Product $product): Response
    {
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('product_index');
        }

        return $this->render('product/edit.html.twig', [
            'product' => $product,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="product_delete", methods={"POST"})
     * @param Request $request
     * @param Product $product
     * @return Response
     */
    public function delete(Request $request, Product $product): Response
    {
        if ($this->isCsrfTokenValid('delete'.$product->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($product);
            $entityManager->flush();
        }

        return $this->redirectToRoute('product_index');
    }
}
